package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String driverID;
    private String licensePlate;
    private Integer parkingLotID;
    private LocalDateTime timeOfArrival;
    private LocalDateTime timeOfDeparture;
    private String Type;//client type

    public Vehicle() {

    }

    public String getDriverID() {
        return driverID;
    }



    public Vehicle(String driverID, String licensePlate, Integer parkingLotID, LocalDateTime timeOfArrival, LocalDateTime timeOfDeparture, String type) {
        this.driverID = driverID;
        this.licensePlate = licensePlate;
        this.parkingLotID = parkingLotID;
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = timeOfDeparture;
        Type = type;
    }

    public int getId() {
        return id;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(Integer parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalDateTime timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public LocalDateTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalDateTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", driverID='" + driverID + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", parkingLotID=" + parkingLotID +
                ", timeOfArrival=" + timeOfArrival +
                ", timeOfDeparture=" + timeOfDeparture +
                ", Type='" + Type + '\'' +
                '}';
    }
}
