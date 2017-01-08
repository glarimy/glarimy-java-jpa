package com.glarimy.hbm;

import javax.persistence.Entity;

@Entity
public class Employee extends Person {
	private static final long serialVersionUID = 8028369986900677881L;
	private double salary;

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", id=" + id + ", name=" + name
				+ ", address=" + address + "]";
	}

}
