package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowAdminReserveParkingEvent {
    private Object parkingLotList;
    private Object worker;
    private  Object subscriptionList;
    public ShowAdminReserveParkingEvent(Message msg) {
        parkingLotList=msg.getObject();
        worker=msg.getObject2();
        subscriptionList=msg.getObject3();
    }

    public Object getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(Object subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public Object getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(Object parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Object getWorker() {
        return worker;
    }

    public void setWorker(Object worker) {
        this.worker = worker;
    }
}
