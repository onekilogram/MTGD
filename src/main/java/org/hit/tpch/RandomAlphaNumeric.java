package org.hit.tpch;

public class RandomAlphaNumeric
        extends AbstractRandomInt
{
    private static final char[] ALPHA_NUMERIC = "0123456789abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ,".toCharArray();

    private static final double LOW_LENGTH_MULTIPLIER = 0.4;
    private static final double HIGH_LENGTH_MULTIPLIER = 1.6;

    private static final int USAGE_PER_ROW = 9;

    private final int minLength;
    private final int maxLength;

    public RandomAlphaNumeric(long seed, int averageLength)
    {
        this(seed, averageLength, 1);
    }

    public RandomAlphaNumeric(long seed, int averageLength, int expectedRowCount)
    {
        super(seed, USAGE_PER_ROW * expectedRowCount);
        this.minLength = (int) (averageLength * LOW_LENGTH_MULTIPLIER);
        this.maxLength = (int) (averageLength * HIGH_LENGTH_MULTIPLIER);
    }

    public String nextValue()
    {
        char[] buffer = new char[nextInt(minLength, maxLength)];//随机生成的

        long charIndex = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (i % 5 == 0) {
                charIndex = nextInt(0, Integer.MAX_VALUE);
            }
            buffer[i] = ALPHA_NUMERIC[(int) (charIndex & 0x3f)];
            charIndex >>= 6;
        }

        return new String(buffer);
    }
}
