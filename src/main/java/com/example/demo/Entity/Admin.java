package com.example.demo.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "hostelId",  nullable = false) // Foreign key to Hostel table
    private Hostel hostel;

    // Constructors
    public Admin() {}

    public Admin(String name, String email, String password, Hostel hostel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.hostel = hostel;
    }

    public Admin(int id, String name, String email, String password, Hostel hostel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hostel = hostel;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
