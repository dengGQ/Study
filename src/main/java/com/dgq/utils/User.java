package com.dgq.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import sun.applet.AppletClassLoader;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int hashCode() {
		return (this.id == null) ? 0 : this.id.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof User) {
			final User obj = (User) object;
			return (this.id != null) ? this.id.equals(obj.id)
					: (obj.id == null);
		}
		return false;
	}

	public static void main(String[] args) {
		/*
		 * System.out.println(System.getProperty("sun.boot.class.path"));
		 * System.out.println(System.getProperty("java.ext.dirs"));
		 */
		
		float a[] = new float[5];
		System.out.println(a[1]);
		
		
	}

}
