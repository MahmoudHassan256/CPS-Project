package il.cshaifasweng.OCSFMediatorExample.entities;

import java.security.NoSuchAlgorithmException;

public class SubsriptionClient extends Client {

    public int RemainingHours;
    public String SubscriptionType;

    public int getRemainingHours() {
        return RemainingHours;
    }

    public void setRemainingHours(int remainingHours) {
        RemainingHours = remainingHours;
    }

    public String getSubscriptionType() {
        return SubscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        SubscriptionType = subscriptionType;
    }

    public SubsriptionClient(String firstName, String lastName, String email, String password, String type, int remainingHours, String subscriptionType) throws NoSuchAlgorithmException {
        super(firstName, lastName, email, password, type);
        RemainingHours = remainingHours;
        SubscriptionType = subscriptionType;
    }

    public SubsriptionClient(int remainingHours, String subscriptionType) {
        RemainingHours = remainingHours;
        SubscriptionType = subscriptionType;
    }

}
