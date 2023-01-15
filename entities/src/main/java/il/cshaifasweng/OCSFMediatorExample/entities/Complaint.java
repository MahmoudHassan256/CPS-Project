package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint implements Serializable {
    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", carNumber='" + carNumber + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", response='" + response + '\'' +
                ", submitionDate=" + submitionDate +
                ", refundValue=" + refundValue +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String carNumber;
    private String status;
    private String description;
    private String response;
    private LocalDateTime submitionDate;
    private String refundValue;




    public String getRefundValue() {
        return refundValue;
    }

    public void setRefundValue(String refundValue) {
        this.refundValue = refundValue;
    }

    public int getId() {
        return id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LocalDateTime getSubmitionDate() {
        return submitionDate;
    }

    public void setSubmitionDate(LocalDateTime submitionDate) {
        this.submitionDate = submitionDate;
    }
    public Complaint() {
    }

    public Complaint(String carNumber, String description, LocalDateTime submitionDate) {
        this.carNumber = carNumber;
        this.description = description;
        this.submitionDate = submitionDate;
        this.status="pending";
    }
}
