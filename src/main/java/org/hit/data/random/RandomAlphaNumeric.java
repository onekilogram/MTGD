package org.hit.data.random;

import org.hit.data.table.Globals;

/**
 * @ClassName RandomAlphaNumeric 
 * @Description 负责生成数字和字母 
 * @author kg 
 * @date   2017年12月27日 下午7:32:42
 */
public class RandomAlphaNumeric
        extends AbstractRandomInt
{
    private static final char[] ALPHA_NUMERIC = "0123456789abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ,".toCharArray();

    private static final double LOW_LENGTH_MULTIPLIER = 0.4;
    private static final double HIGH_LENGTH_MULTIPLIER = 1.6;

    private static final int USAGE_PER_ROW = 9;

    private final int minLength;
    private final int maxLength;

    public RandomAlphaNumeric(long seed)
    {
        this(seed,Globals.String_averageLength, 1);
    }
    
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
            buffer[i] = ALPHA_NUMERIC[(int) (charIndex & 0x3f)];//与63相与
            charIndex >>= 6;
        }

        return new String(buffer);
    }
    public static void main(String[] args) {
		
    	RandomAlphaNumeric randomAlphaNumeric = new RandomAlphaNumeric(System.currentTimeMillis(), 5);
    	
    	for(int i=0;i<9;i++){
    		System.out.println(randomAlphaNumeric.nextValue());
    		randomAlphaNumeric.rowFinished();
    	}
	}
}
