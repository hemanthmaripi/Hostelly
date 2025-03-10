package com.example.demo.Entity;

public class User {

	private String email;
	private String role;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String email, String role, String name) {
		super();
		this.email = email;
		this.role = role;
		this.name = name;
	}

	public User(String email, String role) {
		super();
		this.email = email;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
