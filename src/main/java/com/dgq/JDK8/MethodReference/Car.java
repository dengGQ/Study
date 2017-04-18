package com.dgq.JDK8.MethodReference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Car {
	private String userName;
	public static Car create(final Supplier<Car> supplier){
		return supplier.get();
	}
	
	public static void collide(final Car car){
		System.out.println("Collide "+car.userName);
	}
	
	public void follow (final Car another){
		System.out.println("Following the " + another.userName);
	}
	
	public void repair(){
		System.out.println("Repair "+this.userName);
	}
	
	public static void main(String[] args) {
		final Car car = Car.create(Car::new);
		car.userName = "dgq";
		
		List<Car> cars = Arrays.asList(car);
		
		cars.forEach(Car::collide);
		
		cars.forEach(car::follow);
		
		cars.forEach(Car::repair);
	}
}
