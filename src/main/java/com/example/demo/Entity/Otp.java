package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="otp")
public class Otp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "otp", nullable = false)
	private String otp;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name="createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdat;

	public Otp(Integer id, String otp, String username, LocalDateTime createdat) {
		super();
		this.id = id;
		this.otp = otp;
		this.username = username;
		this.createdat = createdat;
	}

	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Otp(String otp, String username) {
		super();
		this.otp = otp;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	
	
	
	
}
