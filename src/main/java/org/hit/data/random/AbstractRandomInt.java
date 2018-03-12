package org.hit.data.random;
import static com.google.common.base.Preconditions.checkState;

public abstract class AbstractRandomInt<T>
{
    private static final long MULTIPLIER = 16807;//乘数  7^5 
    private static final long MODULUS = 2147483647;//模数  2^31 - 1

    private final int expectedUsagePerRow;//每行的nextRand()的次数

    private long seed;//种子
    private int usage;//用于使用数与expectedUsagePerRow的比较

    /**
     * Creates a new random number generator with the specified seed and
     * specified number of random values per row.
     */
    public AbstractRandomInt(long seed, int expectedUsagePerRow)
    {
        this.seed = seed;
        this.expectedUsagePerRow = expectedUsagePerRow;
    }

    /**
     * Get a random value between lowValue (inclusive) and highValue (inclusive).
     */
    public Integer nextInt(int lowValue, int highValue)
    {
        nextRand();//更新seed

        // This code is strange because we must maintain the bugs in the
        // original TPC-H generator code.

        // This will result in overflow when high is max int and low is 0,
        // which is a bug since you will get a value outside of the
        // specified range.  There is code that relies on this bug.
        int intRange = highValue - lowValue + 1;
        double doubleRange = (double) intRange;
        int valueInRange = (int) ((1.0 * seed / MODULUS) * doubleRange);

        return lowValue + valueInRange;
    }

    protected long nextRand()
    {
        if (usage >= expectedUsagePerRow) {
            checkState(false, "Expected random to be used only %s times per row", expectedUsagePerRow);
        }
        seed = (seed * MULTIPLIER) % MODULUS;
        usage++;
        return seed;
    }

    /**
     * Advances the random number generator to the start of the sequence for
     * the next row.  Each row uses a specified number of random values, so the
     * random number generator can be quickly advanced for partitioned data
     * sets.
     */
    public void rowFinished()
    {
        advanceSeed(expectedUsagePerRow - usage);
        usage = 0;
    }

    /**
     * Advance the specified number of rows.  Advancing to a specific row is
     * needed for partitioned data sets.
     */
    public void advanceRows(long rowCount)
    {
        // finish the current row
        if (usage != 0) {
            rowFinished();
        }

        // advance the seed
        advanceSeed(expectedUsagePerRow * rowCount);
    }

    private void advanceSeed(long count)
    {
        long multiplier = MULTIPLIER;
        while (count > 0) {
        	 if ((count & 1) != 0) {
                seed = (multiplier * seed) % MODULUS;
            }
            // integer division, truncates
            count = count >> 1;
            multiplier = (multiplier * multiplier) % MODULUS;
        }
    }
    public abstract T nextValue();

//	public Integer nextValue(int lowValue, int highValue) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
