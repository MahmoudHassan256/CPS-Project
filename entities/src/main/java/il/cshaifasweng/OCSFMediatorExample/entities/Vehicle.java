package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;
    private String DriverId;
    private String LicensePlateNumber;
    private LocalDateTime ArrivalTime;
    private LocalDateTime ExitingTime;
    private String Type;
    private int floorNumber;
    private int rowNumber;
    private int depthNumber;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Vehicle() {
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getDepthNumber() {
        return depthNumber;
    }

    public void setDepthNumber(int depthNumber) {
        this.depthNumber = depthNumber;
    }

    public Long getId() {
        return id;
    }
    

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getLicensePlateNumber() {
        return LicensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        LicensePlateNumber = licensePlateNumber;
    }

    public LocalDateTime getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public LocalDateTime getExitingTime() {
        return ExitingTime;
    }

    public void setExitingTime(LocalDateTime exitingTime) {
        ExitingTime = exitingTime;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    public void setPosition(int floor,int row,int depth){
    floorNumber=floor;
    rowNumber=row;
    depthNumber=depth;
    }

}
