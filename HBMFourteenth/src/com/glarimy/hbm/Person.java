package com.glarimy.hbm;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person implements Serializable {
	private static final long serialVersionUID = -9061337820645842479L;

	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}