package org.hit.data.random;


import java.math.BigDecimal;
/**
 * @ClassName RandomDecimalInt 
 * @Description  负责产生一个范围的数,有小数点
 * @author kg 
 * @date   2017年12月27日 下午4:07:04
 */
public class RandomDecimal
        extends AbstractRandomInt<String>
{
    private final int lowValue;
    private final int highValue;
    
    private final int decimalPoint;

    //最多保留9位小数
    static int [] num = {1,10,100,1000,10000,100000,1000000,10000000,100000000,1000000000};
    
    public RandomDecimal(long seed, int lowValue, int highValue,int decimalPoint)
    {
        this(seed, lowValue, highValue,decimalPoint, 1); //expectedRowCount=1
    }

    public RandomDecimal(long seed, int lowValue, int highValue, int decimalPoint,int expectedRowCount)
    {
        super(seed, expectedRowCount);
        this.lowValue = lowValue;
        this.highValue = highValue;
        this.decimalPoint = decimalPoint;
    }

    public String nextValue()
    {
        return formatValue(nextInt(lowValue, highValue));
    }
    
    public String nextValueToString(){
    	return formatValue(nextInt(lowValue, highValue));
    }
    
    public String formatValue(long value)
    {
        // todo there must be a better way to do this
        return new BigDecimal(value).divide(new BigDecimal(num[decimalPoint])).setScale(decimalPoint).toString();
    }
    
    public static void main(String[] args) {
		
//    	String fString = "%0"+3+"d";
//    	
//    	String tmp = String.format(ENGLISH, fString,20);
//    	System.out.println(tmp.substring(tmp.length()-3,tmp.length()));
//    	System.out.println(tmp);
    	//String.format(ENGLISH, "19%02d-%02d-%02d", y, m, dy);
    	
    	//测试，确定好最大值和最小值
    	int min = 1;
    	int max = 1000000000;
    	int p = 8;
    	
    	RandomDecimal randomDecimal = new RandomDecimal(System.currentTimeMillis(), min, max*num[p], p);
    	
    	
    	for(int i=0;i<9;i++){
    		String tString = randomDecimal.nextValueToString();
    		randomDecimal.rowFinished();
        	System.out.println(tString);
    	}
	}
}