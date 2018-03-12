package org.hit.data.random;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Random random = new Random();
		int i = random.nextInt(100);
		for(int j =0;j<100;j++){
			System.out.println(random.nextInt(100));
		}
		
	}
}
