package com.example.demo.DTO;


public class HostellerDTO {

	private String name;
	private String phoneno;
	private String email;
	private String password;
	private String address;
	private String aadharCardNo;
	private String emergencyContactNo;
	private int roomNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAadharCardNo() {
		return aadharCardNo;
	}

	public void setAadharCardNo(String aadharCardNo) {
		this.aadharCardNo = aadharCardNo;
	}

	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}

	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}


	public HostellerDTO(String name, String phoneno, String email, String password, String address, String aadharCardNo,
			String emergencyContactNo, int roomNo) {
		super();
		this.name = name;
		this.phoneno = phoneno;
		this.email = email;
		this.password = password;
		this.address = address;
		this.aadharCardNo = aadharCardNo;
		this.emergencyContactNo = emergencyContactNo;
		this.roomNo = roomNo;
	}

	public HostellerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
