package com.glarimy.hbm;

import java.util.Date;

public class Person {
	private int id;
	private String name;
	private int phone;
	private Date dob;
	private boolean indian;
	private double height;
	private String profile;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isIndian() {
		return indian;
	}

	public void setIndian(boolean indian) {
		this.indian = indian;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", dob=" + dob
				+ ", indian=" + indian + ", height=" + height + ", phone="
				+ phone + ", profile=" + profile + "]";
	}


}