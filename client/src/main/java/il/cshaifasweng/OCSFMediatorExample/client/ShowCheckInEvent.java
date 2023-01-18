package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowCheckInEvent {
    private Object parkinglotList;
    private Object reservationList;
    public ShowCheckInEvent(Message msg) {
    parkinglotList=msg.getObject();
    reservationList= msg.getObject2();
    }

    public Object getParkinglotList() {
        return parkinglotList;
    }

    public void setParkinglotList(Object parkinglotList) {
        this.parkinglotList = parkinglotList;
    }

    public Object getReservationList() {
        return reservationList;
    }

    public void setReservationList(Object reservationList) {
        this.reservationList = reservationList;
    }
}
