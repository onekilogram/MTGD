package org.hit.data.random;

import java.util.Random;

/**
 * @ClassName RandomGaussian
 * @Description 高斯分布的函数
 * @author kg
 * @date 2017年12月29日 下午3:19:10
 */
public class RandomGaussian extends AbstractRandomInt<Double> {

	// Math.sqrt(b)*random.nextGaussian()+a 即均值为a，方差为b的随机数
	private Random random; //随机函数
	private double a;  //均值
	private double b;  //方差

	public RandomGaussian(long seed, int expectedUsagePerRow) {
		super(seed, expectedUsagePerRow);
		// TODO Auto-generated constructor stub
		this.random = new Random(seed);
	}

	@Override
	public Double nextValue() {
		// TODO Auto-generated method stub
		Double double1 = Math.sqrt(this.b) * ((this.random).nextGaussian()) + this.a;

		return double1;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public static void main(String[] args) {
		
		
		Random r = new Random();
		  for(int i=0; i<100; i++){
		     // System.out.println(r.nextGaussian());
		  }
		// TODO Auto-generated method stub
		RandomGaussian randomGaussian = new RandomGaussian(System.currentTimeMillis(), 2);

		randomGaussian.a=80;//均值
		randomGaussian.b=25;//方差
	
		for (int i = 0; i < 1000; i++) {
			double tmp =randomGaussian.nextValue();
			if(tmp>90){
			//	System.out.println((int)tmp);
			}
			
		}
		
		//生成一个数组的高斯分布
		randomGaussian.a=5;
		randomGaussian.b=10;
		int sum=0;
		int count=4;
		double s =0;
		int left =0;
		while(true){
			for(int i=0;i<count;i++){
				double tmp =randomGaussian.nextValue();
				if(tmp<0){
					continue;
				}
				if(5-tmp>0){
					left++;
				}
				s = s+(5-tmp)*(5-tmp);
				System.out.println((int)tmp);
				sum+=(int)tmp;
			}
			System.out.println(sum);
			System.out.println(s);
			if(sum==(count*randomGaussian.a) && (randomGaussian.b*randomGaussian.b-s)<=10 && left==2)
				break;
			
			sum=0;
			s=0;
			left=0;
		}
		
		
	}
}
