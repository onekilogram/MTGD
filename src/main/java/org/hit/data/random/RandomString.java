package org.hit.data.random;

import org.hit.data.text.Distribution;

public class RandomString
        extends AbstractRandomInt<String>
{
    private final Distribution distribution;

    public RandomString(long seed, Distribution distribution)
    {
        this(seed, distribution, 1);
    }

    public RandomString(long seed, Distribution distribution, int expectedRowCount)
    {
        super(seed, expectedRowCount);
        this.distribution = distribution;
    }

    public String nextValue()
    {
        return distribution.randomValue(new RandomInt(System.currentTimeMillis(), 3));
    }
}