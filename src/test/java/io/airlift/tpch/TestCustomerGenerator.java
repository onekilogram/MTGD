package io.airlift.tpch;

import org.hit.tpch.CustomerGenerator;
import org.hit.tpch.TpchEntity;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.testng.annotations.Test;

import com.google.common.base.Charsets;

import static io.airlift.tpch.GeneratorAssertions.assertEntityLinesMD5;

public class TestCustomerGenerator {
	@Test
	public void testScaleFactor1() {
		
		Iterable<? extends TpchEntity> entities= new CustomerGenerator(1, 1, 1500);
		
		//System.out.println(calculateStartIndex(150000, 1, 150, 150000)); //初始行号 从1开始
		//
		//System.out.println(calculateRowCount(150000, 1, 150, 1500000));  //终止行号 150000/1500=100
		
		//entities
		System.out.println();
		
		
		for (TpchEntity entity : entities) {
			System.out.println(new String(entity.toLine().getBytes(Charsets.UTF_8)));
		}
		
	}


	public static void assertPartialMD5(int scaleFactor, int step, int children,
			String expectedMD5) {
		assertEntityLinesMD5(new CustomerGenerator(scaleFactor, step, children), expectedMD5);
	}
}
