package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Complaint {

	public Complaint(int id, String ownerDescription, String status) {
		super();
		this.id = id;
		this.ownerDescription = ownerDescription;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "hosteller_id", nullable = false)
	private Hosteller hosteller;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "hostel_id", nullable = false)
	private Hostel hostel;

	private LocalDateTime date = LocalDateTime.now();

	private String category;

	private String description;

	private String ownerDescription;

	private String status = "Pending";

	
	
	public Complaint(String category, String description) {
		super();
		this.category = category;
		this.description = description;
	}
	
	

	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Complaint(String category, String description, String status) {
		super();
		this.category = category;
		this.description = description;
		this.status = status;
	}

	public Complaint(int id, Hosteller hosteller, Hostel hostel, LocalDateTime date, String category, String description,
			String ownerDescription, String status) {
		super();
		this.id = id;
		this.hosteller = hosteller;
		this.hostel = hostel;
		this.date = date;
		this.category = category;
		this.description = description;
		this.ownerDescription = ownerDescription;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hosteller getHosteller() {
		return hosteller;
	}

	public void setHosteller(Hosteller hosteller) {
		this.hosteller = hosteller;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnerDescription() {
		return ownerDescription;
	}

	public void setOwnerDescription(String ownerDescription) {
		this.ownerDescription = ownerDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Default status

	// Getters and Setters
}
