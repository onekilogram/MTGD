package org.hit.data.text;

import static com.google.common.base.Charsets.US_ASCII;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.hit.data.random.RandomInt;

public class TextPool
{
    private static final int DEFAULT_TEXT_POOL_SIZE = 300 * 1024 * 1024;
    private static final int MAX_SENTENCE_LENGTH = 256;

    private static TextPool DEFAULT_TEXT_POOL;

    public static synchronized TextPool getDefaultTestPool()
    {
        if (DEFAULT_TEXT_POOL == null) {
            DEFAULT_TEXT_POOL = new TextPool(DEFAULT_TEXT_POOL_SIZE, Distributions.getDefaultDistributions());
        }
//        System.out.println(DEFAULT_TEXT_POOL.textPool.length+".<--kg");
//        System.out.println(DEFAULT_TEXT_POOL.textPool);
//        System.out.println(new String(DEFAULT_TEXT_POOL.textPool));
        return DEFAULT_TEXT_POOL;
    }

    private final byte[] textPool;
    private final int textPoolSize;

    public TextPool(int size, Distributions distributions)
    {
        this(size, distributions, new TextGenerationProgressMonitor()
        {
            public void updateProgress(double progress)
            {
            }
        });
    }

    public TextPool(int size, Distributions distributions, TextGenerationProgressMonitor monitor)
    {
        checkNotNull(distributions, "distributions is null");
        checkNotNull(monitor, "monitor is null");

        ByteArrayBuilder output = new ByteArrayBuilder(size + MAX_SENTENCE_LENGTH);

        RandomInt randomInt = new RandomInt(933588178, Integer.MAX_VALUE);

        while (output.getLength() < size) {
            generateSentence(distributions, output, randomInt);
            monitor.updateProgress(Math.min(1.0 * output.getLength() / size, 1.0));
        }
        output.erase(output.getLength() - size);
        textPool = output.getBytes();
        textPoolSize = output.getLength();

//        System.out.println(textPoolSize);
//        //
//        FileWriter  fos = null;
//		try {
//			fos = new FileWriter("out.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			fos.write(new String(output.getBytes()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			fos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }

    public int size()
    {
        return textPoolSize;
    }

    public String getText(int begin, int end)
    {
        if (end > textPoolSize) {
            throw new IndexOutOfBoundsException(format("Index %d is beyond end of text pool (size = %d)", end, textPoolSize));
        }
        return new String(textPool, begin, end - begin, US_ASCII);
    }

    private static void generateSentence(Distributions distributions, ByteArrayBuilder builder, RandomInt random)
    {
        String syntax = distributions.getGrammars().randomValue(random);

        int maxLength = syntax.length();
        for (int i = 0; i < maxLength; i += 2) {
            switch (syntax.charAt(i)) {
                case 'V':
                    generateVerbPhrase(distributions, builder, random);
                    break;
                case 'N':
                    generateNounPhrase(distributions, builder, random);
                    break;
                case 'P':
                    String preposition = distributions.getPrepositions().randomValue(random);
                    builder.append(preposition);
                    builder.append(" the ");
                    generateNounPhrase(distributions, builder, random);
                    break;
                case 'T':
                    // trim trailing space
                    // terminators should abut previous word
                    builder.erase(1);
                    String terminator = distributions.getTerminators().randomValue(random);
                    builder.append(terminator);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown token '" + syntax.charAt(i) + "'");
            }
            if (builder.getLastChar() != ' ') {
                builder.append(" ");
            }
        }
    }

    private static void generateVerbPhrase(Distributions distributions, ByteArrayBuilder builder, RandomInt random)
    {
        String syntax = distributions.getVerbPhrase().randomValue(random);
        int maxLength = syntax.length();
        for (int i = 0; i < maxLength; i += 2) {
            Distribution source;
            switch (syntax.charAt(i)) {
                case 'D':
                    source = distributions.getAdverbs();
                    break;
                case 'V':
                    source = distributions.getVerbs();
                    break;
                case 'X':
                    source = distributions.getAuxiliaries();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown token '" + syntax.charAt(i) + "'");
            }

            // pick a random word
            String word = source.randomValue(random);
            builder.append(word);

            // add a space
            builder.append(" ");
        }
    }

    private static void generateNounPhrase(Distributions distributions, ByteArrayBuilder builder, RandomInt random)
    {
        String syntax = distributions.getNounPhrase().randomValue(random);
        int maxLength = syntax.length();
        for (int i = 0; i < maxLength; i++) {
            Distribution source;
            switch (syntax.charAt(i)) {
                case 'A':
                    source = distributions.getArticles();
                    break;
                case 'J':
                    source = distributions.getAdjectives();
                    break;
                case 'D':
                    source = distributions.getAdverbs();
                    break;
                case 'N':
                    source = distributions.getNouns();
                    break;
                case ',':
                    builder.erase(1);
                    builder.append(", ");
                case ' ':
                    continue;
                default:
                    throw new IllegalArgumentException("Unknown token '" + syntax.charAt(i) + "'");
            }
            // pick a random word
            String word = source.randomValue(random);
            builder.append(word);

            // add a space
            builder.append(" ");
        }
    }

    public interface TextGenerationProgressMonitor
    {
        void updateProgress(double progress);
    }

    private static class ByteArrayBuilder
    {
        private int length;
        private final byte[] bytes;

        public ByteArrayBuilder(int size)
        {
            this.bytes = new byte[size];
        }

        public void append(String string)
        {
            // This is safe because the data is ASCII
            //noinspection deprecation
            string.getBytes(0, string.length(), bytes, length);
            length += string.length();
        }

        public void erase(int count)
        {
            checkState(length >= count, "not enough bytes to erase");
            length -= count;
        }

        public int getLength()
        {
            return length;
        }

        public byte[] getBytes()
        {
            return bytes;
        }

        public char getLastChar()
        {
            return (char) bytes[length - 1];
        }
    }
}
