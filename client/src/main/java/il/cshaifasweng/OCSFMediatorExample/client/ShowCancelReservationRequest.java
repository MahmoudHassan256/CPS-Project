package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowCancelReservationRequest {
    private Object reservationList;

    public Object getReservationList() {
        return reservationList;
    }

    public void setReservationList(Object reservationList) {
        this.reservationList = reservationList;
    }

    public ShowCancelReservationRequest(Message msg) {
        this.reservationList = msg.getObject();
    }
}
