package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Room {

    @EmbeddedId
    private RoomId id;

    @ManyToOne
    @MapsId("hostel_id")  // Matches exactly the field name in RoomId
    @JoinColumn(name = "hostel_id", nullable = false)
    @JsonIgnore
    private Hostel hostel;

    private int capacity; 

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hosteller> hostellers = new ArrayList<>();

    public Room() {}

    public Room(RoomId id, Hostel hostel, int capacity) {
        this.id = id;
        this.hostel = hostel;
        this.capacity = capacity;
    }

    public RoomId getId() {
        return id;
    }

    public void setId(RoomId id) {
        this.id = id;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
