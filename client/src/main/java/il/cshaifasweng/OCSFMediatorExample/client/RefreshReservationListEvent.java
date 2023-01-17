package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class RefreshReservationListEvent {
    private Object reservationList;
    public RefreshReservationListEvent(Message msg) {
    reservationList=msg.getObject();
    }

    public Object getReservationList() {
        return reservationList;
    }

    public void setReservationList(Object reservationList) {
        this.reservationList = reservationList;
    }
}
