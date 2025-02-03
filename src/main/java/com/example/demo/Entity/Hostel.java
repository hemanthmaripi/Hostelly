package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hostels")
public class Hostel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hostelid;
	
	@Column(name="hostelname", nullable = false)
	private String hostelname;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name = "contactnumber", nullable = false, unique = true)
	private String contactnumber;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "address", nullable = false, columnDefinition = "TEXT")
	private String address;
	
	@Column(name = "password", nullable = false)
	private String password;

	public Hostel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hostel(Integer hostelid, String hostelname, String username, String contactnumber, String email,
			String address, String password) {
		super();
		this.hostelid = hostelid;
		this.hostelname = hostelname;
		this.username = username;
		this.contactnumber = contactnumber;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public Hostel(String hostelname, String username, String contactnumber, String email, String address, String password) {
		super();
		this.hostelname = hostelname;
		this.username = username;
		this.contactnumber = contactnumber;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getHostelid() {
		return hostelid;
	}

	public void setHostelid(Integer hostelid) {
		this.hostelid = hostelid;
	}

	public String getHostelname() {
		return hostelname;
	}

	public void setHostelname(String hostelname) {
		this.hostelname = hostelname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
