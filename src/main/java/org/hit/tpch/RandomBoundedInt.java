package org.hit.tpch;

public class RandomBoundedInt
        extends AbstractRandomInt
{
    private final int lowValue;
    private final int highValue;

    public RandomBoundedInt(long seed, int lowValue, int highValue)
    {
        this(seed, lowValue, highValue, 1); //expectedRowCount=1
    }

    public RandomBoundedInt(long seed, int lowValue, int highValue, int expectedRowCount)
    {
        super(seed, expectedRowCount);
        this.lowValue = lowValue;
        this.highValue = highValue;
    }

    public int nextValue()
    {
        return nextInt(lowValue, highValue);
    }
}