package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "refund")
public class Refund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String refundDescription;
    private String refundValue;

    public Refund() {
    }

    public Refund(String refundDescription, String refundValue) {
        this.refundDescription = refundDescription;
        this.refundValue = refundValue;
    }

    public int getId() {
        return id;
    }

    public String getRefundDescription() {
        return refundDescription;
    }

    public void setRefundDescription(String refundDescription) {
        this.refundDescription = refundDescription;
    }

    public String getRefundValue() {
        return refundValue;
    }

    public void setRefundValue(String refundValue) {
        this.refundValue = refundValue;
    }
}
