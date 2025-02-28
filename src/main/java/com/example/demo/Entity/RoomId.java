package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class RoomId implements Serializable {  

    private int roomNo;
    private int hostel_id;

    public RoomId() {}

    public RoomId(int roomNo, int hostel_id) {
        this.roomNo = roomNo;
        this.hostel_id = hostel_id;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getHostelId() {
        return hostel_id;
    }

    public void setHostelId(int hostelId) {
        this.hostel_id = hostelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomId roomId = (RoomId) o;
        return roomNo == roomId.roomNo && hostel_id == roomId.hostel_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo, hostel_id);
    }
}
