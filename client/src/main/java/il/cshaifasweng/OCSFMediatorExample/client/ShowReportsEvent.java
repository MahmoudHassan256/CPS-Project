package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowReportsEvent {
    private Object reservationList;
    private Object complaintList;
    private Object worker;
    public ShowReportsEvent(Message msg) {
        reservationList=msg.getObject();
        complaintList=msg.getObject2();
        worker=msg.getObject3();
    }

    public Object getReservationList() {
        return reservationList;
    }

    public void setReservationList(Object reservationList) {
        this.reservationList = reservationList;
    }

    public Object getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(Object complaintList) {
        this.complaintList = complaintList;
    }

    public Object getWorker() {
        return worker;
    }

    public void setWorker(Object worker) {
        this.worker = worker;
    }
}
