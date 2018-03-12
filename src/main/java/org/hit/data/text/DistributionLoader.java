package org.hit.data.text;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharSource;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Iterators.filter;

public class DistributionLoader
{
    public static <R extends Readable & Closeable> Map<String, Distribution> loadDistribution(CharSource input)
            throws IOException
    {
        Iterator<String> iterator = filter(input.readLines().iterator(), new Predicate<String>()
        {
            @Override
            public boolean apply(String line)
            {
                line = line.trim();
                return !line.isEmpty() && !line.startsWith("#");
            }
        });
         Map<String, Distribution> map = loadDistributions(iterator);
         
//         for(Map.Entry<String, Distribution> entry : map.entrySet()){
//        	 
//        	 System.out.println(entry.getKey()+"->"+entry.getValue().toString());
//         }

        return map;
    }

    private static Distribution loadDistribution(Iterator<String> lines, String name)
    {
        int count = -1;
        ImmutableMap.Builder<String, Integer> members = ImmutableMap.builder();
        while (lines.hasNext()) {
            // advance to "begin"
            String line = lines.next();
            if (isEnd(name, line)) {
                Map<String, Integer> weights = members.build();
                checkState(count == weights.size(),
                        "Expected %d entries in distribution %s, but only %d entries were found",
                        count,
                        weights.size());
                return new Distribution(name, weights);
            }

            List<String> parts = ImmutableList.copyOf(Splitter.on('|').trimResults().omitEmptyStrings().split(line));
            checkState(parts.size() == 2,
                    "Expected line to contain two parts, but it contains %d parts: %s",
                    parts.size(),
                    line);

            String value = parts.get(0);
            int weight;
            try {
                weight = Integer.parseInt(parts.get(1));
            }
            catch (NumberFormatException e) {
                throw new IllegalStateException(String.format("Invalid distribution %s: invalid weight on line %s", name, line));
            }

            if (value.equalsIgnoreCase("count")) {
                count = weight;
            }
            else {
                members.put(value, weight);
            }
        }
        throw new IllegalStateException(String.format("Invalid distribution %s: no end statement", name));
    }

    private static boolean isEnd(String name, String line)
    {
        List<String> parts = ImmutableList.copyOf(Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().split(line));
        if (parts.get(0).equalsIgnoreCase("END")) {
            checkState(parts.size() == 2 && parts.get(1).equalsIgnoreCase(name),
                    "Expected end statement be 'END %s', but was '%s'", name, line);
            return true;
        }
        return false;
    }

    private static Map<String, Distribution> loadDistributions(Iterator<String> lines)
    {
        ImmutableMap.Builder<String, Distribution> distributions = ImmutableMap.builder();
        while (lines.hasNext()) {
            // advance to "begin"
            String line = lines.next();
            List<String> parts = ImmutableList.copyOf(Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().split(line));
            if (parts.size() != 2) {
                continue;
            }


            if (parts.get(0).equalsIgnoreCase("BEGIN")) {
                String name = parts.get(1);
                Distribution distribution = loadDistribution(lines, name);
                distributions.put(name.toLowerCase(), distribution);
            }
        }
        return distributions.build();
    }
}
