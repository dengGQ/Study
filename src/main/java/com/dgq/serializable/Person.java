package com.dgq.serializable;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name = null;

	transient private Integer age = null;

	private Gender gender = null;

	private static class InstanceHolder {
		private static final Person instance = new Person("dgq", 12,
				Gender.MALE);
	}

	public static Person getInstance() {
		return InstanceHolder.instance;
	}

	private Person() {
		System.out.println("non-arg constructor");
	}

	private Person(String name, Integer age, Gender gender) {
		System.out.println("arg constructor");
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "[" + name + "," + gender + "," + age + "]";
	}

	private Object readResolve() throws ObjectStreamException {
		return InstanceHolder.instance;
	}
}
