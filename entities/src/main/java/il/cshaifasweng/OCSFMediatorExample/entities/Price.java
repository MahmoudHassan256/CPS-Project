package il.cshaifasweng.OCSFMediatorExample.entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="prices")
public class Price implements Serializable {
    public Price(String parkingType, String paymentPlan, String price) {
        this.parkingType = parkingType;
        this.paymentPlan = paymentPlan;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String parkingType;
    private String paymentPlan;
    private String price;

    public Price() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
