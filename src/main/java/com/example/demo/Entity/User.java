package com.example.demo.Entity;

public class User {

	private String email;
	private String role;
	private String name;
	private Hostel hostel;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String email, String role, String name, Hostel hostel, int id) {
		super();
		this.hostel = hostel;
		this.email = email;
		this.role = role;
		this.name = name;
		this.id = id;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
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
