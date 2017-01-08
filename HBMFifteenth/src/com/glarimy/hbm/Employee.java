package com.glarimy.hbm;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends Person {
	private int eid;
	private double salary;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", salary=" + salary + ", toString()="
				+ super.toString() + "]";
	}

}
