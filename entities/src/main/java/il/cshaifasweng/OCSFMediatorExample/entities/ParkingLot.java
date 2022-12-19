package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="parking_lots")
public class ParkingLot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int capacity;
    public ParkingLot(int capacity) {
        super();
        this.capacity = capacity;
    }

    public ParkingLot() {

    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
