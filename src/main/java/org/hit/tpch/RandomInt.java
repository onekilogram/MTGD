package org.hit.tpch;
public class RandomInt
        extends AbstractRandomInt
{
    public RandomInt(long seed, int expectedUsagePerRow)
    {
        super(seed, expectedUsagePerRow);
    }

    @Override
    public int nextInt(int lowValue, int highValue)
    {
        return super.nextInt(lowValue, highValue);
    }
}