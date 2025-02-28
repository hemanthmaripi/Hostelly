package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hosteller")
public class Hosteller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneno;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)  // Address is stored as a string in DB
    private String address;

    @Column(nullable = false, unique = true)
    private String aadharCardNo;

    @Column(nullable = false)
    private String emergencyContactNo;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "room_no", referencedColumnName = "roomNo"),
        @JoinColumn(name = "hostel_id", referencedColumnName = "hostel_id")
    })
    private Room room;

    public Hosteller() {}

    public Hosteller(String name, String phoneno, String email, String password, String address, 
                     String aadharCardNo, String emergencyContactNo, Room room) {
        this.name = name;
        this.phoneno = phoneno;
        this.email = email;
        this.password = password;
        this.address = address;
        this.aadharCardNo = aadharCardNo;
        this.emergencyContactNo = emergencyContactNo;
        this.room = room;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
