package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowMyProfileEvent {
    private Object reservationList;
    private Object subscriptionList;
    public ShowMyProfileEvent(Message msg) {
    reservationList=msg.getObject();
    subscriptionList=msg.getObject2();
    }

    public Object getReservationList() {
        return reservationList;
    }

    public void setReservationList(Object reservationList) {
        this.reservationList = reservationList;
    }

    public Object getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(Object subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
