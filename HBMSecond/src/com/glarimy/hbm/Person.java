package com.glarimy.hbm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "People")
public class Person {
	@Id
	@GeneratedValue
	private int id;

	@Column(unique = true)
	private String name;

	@Column(nullable = true)
	private int phone;

	@Column(name = "Date_of_Birth")
	@Temporal(TemporalType.DATE)
	private Date dob;

	private boolean indian;

	@Column(precision = 2, scale = 2)
	private double height;

	@Transient
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