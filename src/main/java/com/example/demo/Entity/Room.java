package com.example.demo.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "hostelid", nullable = false)
	private Integer hostelid;
	
	@Column(name = "roomnumber", nullable = false)
	private Integer roomnumber;
	
	@Column(name = "capacity", nullable = false)
	private Integer capacity;
	
	@Column(nullable = false, name = "occupancy", columnDefinition = "INT DEFAULT 0")
	private Integer occupancy;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, columnDefinition = "ENUM('single', 'double', 'triple', 'quadraple') DEFAULT 'triple'")
	private RoomType type;
	
	@Column(nullable = false, name="rent")
	private BigDecimal rent;

	public Room(Integer id, Integer hostelid, Integer roomnumber, Integer capacity, Integer occupancy, RoomType type,
			BigDecimal rent) {
		super();
		this.id = id;
		this.hostelid = hostelid;
		this.roomnumber = roomnumber;
		this.capacity = capacity;
		this.occupancy = occupancy;
		this.type = type;
		this.rent = rent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHostelid() {
		return hostelid;
	}

	public void setHostelid(Integer hostelid) {
		this.hostelid = hostelid;
	}

	public Integer getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(Integer roomnumber) {
		this.roomnumber = roomnumber;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	
	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(Integer id, Integer hostelid, Integer roomnumber, Integer capacity, Integer occupancy, RoomType type) {
		super();
		this.id = id;
		this.hostelid = hostelid;
		this.roomnumber = roomnumber;
		this.capacity = capacity;
		this.occupancy = occupancy;
		this.type = type;
	}
	
}
