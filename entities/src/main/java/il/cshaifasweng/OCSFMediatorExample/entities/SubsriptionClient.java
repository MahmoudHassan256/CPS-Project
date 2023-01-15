package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

public class SubsriptionClient extends Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String driverId;
    public String SubscriptionType;
    public int desiredPrkinglot;
    public LocalDateTime subscriptionStartDate;
    public LocalDateTime timeOfDepature;
    public int numberOfCars;
    public List<String> carNumberList;
    public String creditCardNumber;
    public LocalDateTime expDate;
    public String cvv;
    public String idHolder;
    public String email;
    public int RemainingHours;

    public SubsriptionClient(String firstName, String lastName, String email, String password, String type, String driverId, String subscriptionType, int desiredPrkinglot, LocalDateTime subscriptionStartDate, LocalDateTime timeOfDepature, int numberOfCars, List<String> carNumberList, String creditCardNumber, LocalDateTime expDate, String cvv, String idHolder, String email1, int remainingHours) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password, type);
        this.driverId = driverId;
        SubscriptionType = subscriptionType;
        this.desiredPrkinglot = desiredPrkinglot;
        this.subscriptionStartDate = subscriptionStartDate;
        this.timeOfDepature = timeOfDepature;
        this.numberOfCars = numberOfCars;
        this.carNumberList = carNumberList;
        this.creditCardNumber = creditCardNumber;
        this.expDate = subscriptionStartDate.plusDays(28);
        this.cvv = cvv;
        this.idHolder = idHolder;
        this.email = email1;
        RemainingHours = remainingHours;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getSubscriptionType() {
        return SubscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        SubscriptionType = subscriptionType;
    }

    public int getDesiredPrkinglot() {
        return desiredPrkinglot;
    }

    public void setDesiredPrkinglot(int desiredPrkinglot) {
        this.desiredPrkinglot = desiredPrkinglot;
    }

    public LocalDateTime getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(LocalDateTime subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public LocalDateTime getTimeOfDepature() {
        return timeOfDepature;
    }

    public void setTimeOfDepature(LocalDateTime timeOfDepature) {
        this.timeOfDepature = timeOfDepature;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public List<String> getCarNumberList() {
        return carNumberList;
    }

    public void setCarNumberList(List<String> carNumberList) {
        this.carNumberList = carNumberList;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getIdHolder() {
        return idHolder;
    }

    public void setIdHolder(String idHolder) {
        this.idHolder = idHolder;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public int getRemainingHours() {
        return RemainingHours;
    }

    public void setRemainingHours(int remainingHours) {
        RemainingHours = remainingHours;
    }
}
