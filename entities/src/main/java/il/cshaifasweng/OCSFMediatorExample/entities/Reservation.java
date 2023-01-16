package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table (name="Reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String driverID;
    private String licensePlate;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", driverID='" + driverID + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", parkingLotID=" + parkingLotID +
                ", timeOfArrival=" + timeOfArrival +
                ", timeOfDeparture=" + timeOfDeparture +
                ", email='" + email + '\'' +
                ", typeOfClient='" + typeOfClient + '\'' +
                ", subsriptionID='" + subsriptionID + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                ", creditCardHolderID='" + creditCardHolderID + '\'' +
                '}';
    }

    private Integer parkingLotID;
    private LocalDateTime timeOfArrival;
    @Column(nullable = true)
    private LocalDateTime timeOfDeparture;
    private String email;
    private String typeOfClient;
    @Column(nullable = true)
    private String subsriptionID;
    @Column(nullable = true)
    private String creditCardNumber;
    @Column(nullable = true)
    private LocalDate expirationDate;
    @Column(nullable = true)
    private String cvv;
    @Column(nullable = true)
    private String creditCardHolderID;

    public Reservation() {
    }
    public Reservation(String driverID, String licensePlate,
                       Integer parkingLotID, LocalDateTime timeOfArrival, LocalDateTime timeOfDeparture,
                       String email, String typeOfClient, String subsriptionID) {
        this.driverID = driverID;
        this.licensePlate = licensePlate;
        this.parkingLotID = parkingLotID;
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = timeOfDeparture;
        this.email = email;
        this.typeOfClient = typeOfClient;
        this.subsriptionID = subsriptionID;
    }

    public Reservation(String driverID, String licensePlate, Integer parkingLotID, LocalDateTime timeOfArrival,
                       LocalDateTime timeOfDeparture, String email,
                       String typeOfClient, String creditCardNumber, LocalDate expirationDate,
                       String cvv, String creditCardHolderID) {
        this.driverID = driverID;
        this.licensePlate = licensePlate;
        this.parkingLotID = parkingLotID;
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = timeOfDeparture;
        this.email = email;
        this.typeOfClient = typeOfClient;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.creditCardHolderID = creditCardHolderID;
    }

    public int getId() {
        return id;
    }

    public String getDriverID() {
        return driverID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeOfClient() {
        return typeOfClient;
    }

    public void setTypeOfClient(String typeOfClient) {
        this.typeOfClient = typeOfClient;
    }

    public String getSubsriptionID() {
        return subsriptionID;
    }

    public void setSubsriptionID(String subsriptionID) {
        this.subsriptionID = subsriptionID;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCreditCardHolderID() {
        return creditCardHolderID;
    }

    public void setCreditCardHolderID(String creditCardHolderID) {
        this.creditCardHolderID = creditCardHolderID;
    }
}