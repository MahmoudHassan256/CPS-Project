package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowCancelReservationRequest {
    private Object reservationList;
    private Object subscriptionList;
    private Object prices;

    public Object getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(Object subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public Object getPrices() {
        return prices;
    }

    public void setPrices(Object prices) {
        this.prices = prices;
    }

    public Object getReservationList() {
        return reservationList;
    }

    public void setReservationList(Object reservationList) {
        this.reservationList = reservationList;
    }

    public ShowCancelReservationRequest(Message msg) {
        this.reservationList = msg.getObject();
        this.subscriptionList = msg.getObject2();
        this.prices = msg.getObject3();
    }
}
