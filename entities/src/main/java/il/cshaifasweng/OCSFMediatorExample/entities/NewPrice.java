package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class NewPrice implements Serializable  {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String parkingType;
    private String paymentPlan;
    private String price;
    private String newPrice;
    private int priceId;

    public NewPrice() {
    }

    public int getPriceId() {
        return priceId;
    }

    public int getId() {
        return id;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String NewPrice) {
        this.newPrice = NewPrice;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
