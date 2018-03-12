package org.hit.tpch;

import java.util.Optional;

import java.util.Objects;

public class TpchColumnType {
	public enum Base {
		INTEGER, IDENTIFIER, DATE, DOUBLE, VARCHAR
	}

	private final Base base;
	private final Optional<Long> precision;
	private final Optional<Long> scale;

	private TpchColumnType(Base base, Optional<Long> precision, Optional<Long> scale) {
		this.base = base;
		this.precision = precision;
		this.scale = scale;
	}

	TpchColumnType(Base base, long precision, long scale) {
		this(base, Optional.of(precision), Optional.of(scale));
	}

	TpchColumnType(Base base, long precision) {
		this(base, Optional.of(precision), Optional.<Long>empty());
	}

	TpchColumnType(Base base) {
		this(base, Optional.<Long>empty(), Optional.<Long>empty());
	}

	public Base getBase() {
		return base;
	}

	public Optional<Long> getPrecision() {
		return precision;
	}

	public Optional<Long> getScale() {
		return scale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TpchColumnType that = (TpchColumnType) o;
		return Objects.equals(base, that.base) && Objects.equals(precision, that.precision)
				&& Objects.equals(scale, that.scale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(base, precision, scale);
	}

	public static void main(String[] args) {
		// 调用工厂方法创建Optional实例
		Optional<String> name = Optional.of("Sanaulla");
		// 传入参数为null，抛出NullPointerException.
		Optional<String> someNull = Optional.ofNullable(null);

		if (name.isPresent()) {
			System.out.println("name is " + name.get());
			System.out.printf("%s\n", name.get());// 类似c中的
		}
		if (someNull.isPresent()) {
			System.out.println("someNull is " + someNull.get());
		}

		// ifPresent方法接受lambda表达式作为参数。
		// lambda表达式对Optional的值调用consumer进行处理。
		name.ifPresent((value) -> {
			System.out.println("The length of the value is: " + value.length());
		});

		// 如果值不为null，orElse方法返回Optional实例的值。
		// 如果为null，返回传入的消息。
		// 输出：There is no value present!
		System.out.println(someNull.orElse("someNull -> There is no value present!"));
		// 输出：Sanaulla
		System.out.println(name.orElse("There is some value!"));

		// orElseGet与orElse方法类似，区别在于orElse传入的是默认值，
		// orElseGet可以接受一个lambda表达式生成默认值。
		// 输出：Default Value
		System.out.println(someNull.orElseGet(() -> "Default Value"));
		// 输出：Sanaulla
		System.out.println(name.orElseGet(() -> "Default Value"));
		
		//map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
		//为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
		Optional<String> upperName = name.map((value) -> value.toUpperCase());
		System.out.println(upperName.orElse("No value found"));
		
		//filter方法检查给定的Option值是否满足某些条件。
		//如果满足则返回同一个Option实例，否则返回空Optional。
		Optional<String> longName = name.filter((value) -> value.length() > 6);
		System.out.println(longName.orElse("The name is less than 6 characters"));//输出Sanaulla
		 
		//另一个例子是Optional值不满足filter指定的条件。
		Optional<String> anotherName = Optional.of("Sana");
		Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
		//输出：name长度不足6字符
		System.out.println(shortName.orElse("The name is less than 6 characters"));

	}
}
