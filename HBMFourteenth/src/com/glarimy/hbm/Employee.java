package com.glarimy.hbm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee extends Person {

	private static final long serialVersionUID = -6198148032756999209L;
	@Id @GeneratedValue
	private int id;
	private double salary;

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary
				+ "]";
	}
}
