package com.glarimy.hbm.common;

public class Author {
	private String name;
	private long phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", phone=" + phone + "]";
	}

}
