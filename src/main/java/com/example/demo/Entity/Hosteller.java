package com.example.demo.Entity;

import java.math.BigDecimal;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hostellers")
public class Hosteller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="hostelid", nullable = false)
	private Hostel hostel;
	
	@ManyToOne
	@JoinColumn(name="roomid", nullable = false)
	private Room room;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private String Gender;
	
	@Column(name = "dob", nullable = false)
	private Date dob;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phoneno", nullable = false)
	private String phoneno;
	
	@Column(name = "guardianname", nullable = false)
	private String guardianname;
	
	@Column(name = "guardianno", nullable = false)
	private String guardianno;
	
	@Column(name = "address", nullable = false, columnDefinition = "TEXT")
	private String address;
	
	@Column(name = "aadharno", nullable = false)
	private String aadharno;
	
	@Column(name = "aadharimage", nullable = false)
	private String aadharimage;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "feestatus",columnDefinition = "ENUM('PAID', 'UNPAID') DEFAULT 'UNPAID'")
	private FeeStatus feestatus;
	
	@Column(name = "rent")
	private BigDecimal rent;

	public Hosteller(Integer id, Hostel hostel, Room room, String name, String gender, Date dob, String email,
			String phoneno, String guardianname, String guardianno, String address, String aadharno, String aadharimage,
			FeeStatus feestatus, BigDecimal rent) {
		super();
		this.id = id;
		this.hostel = hostel;
		this.room = room;
		this.name = name;
		Gender = gender;
		this.dob = dob;
		this.email = email;
		this.phoneno = phoneno;
		this.guardianname = guardianname;
		this.guardianno = guardianno;
		this.address = address;
		this.aadharno = aadharno;
		this.aadharimage = aadharimage;
		this.feestatus = feestatus;
		this.rent = rent;
	}

	public Hosteller(String name, String gender, Date dob, String email, String phoneno, String guardianname,
			String guardianno, String address, String aadharno, String aadharimage, FeeStatus feeStatus) {
		super();
		this.name = name;
		Gender = gender;
		this.dob = dob;
		this.email = email;
		this.phoneno = phoneno;
		this.guardianname = guardianname;
		this.guardianno = guardianno;
		this.address = address;
		this.aadharno = aadharno;
		this.aadharimage = aadharimage;
		this.feestatus = feeStatus;
	}
		
	public Hosteller(Integer id,String name, String gender, Date dob, String email, String phoneno, String guardianname,
			String guardianno, String address, String aadharno, String aadharimage, FeeStatus feeStatus) {
		super();
		this.id = id;
		this.name = name;
		Gender = gender;
		this.dob = dob;
		this.email = email;
		this.phoneno = phoneno;
		this.guardianname = guardianname;
		this.guardianno = guardianno;
		this.address = address;
		this.aadharno = aadharno;
		this.aadharimage = aadharimage;
		this.feestatus = feeStatus;
	}
	
	public Hosteller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getGuardianname() {
		return guardianname;
	}

	public void setGuardianname(String guardianname) {
		this.guardianname = guardianname;
	}

	public String getGuardianno() {
		return guardianno;
	}

	public void setGuardianno(String guardianno) {
		this.guardianno = guardianno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAadharno() {
		return aadharno;
	}

	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}

	public String getAadharimage() {
		return aadharimage;
	}

	public void setAadharimage(String aadharimage) {
		this.aadharimage = aadharimage;
	}

	public FeeStatus getFeestatus() {
		return feestatus;
	}

	public void setFeestatus(FeeStatus feestatus) {
		this.feestatus = feestatus;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	
	
}
