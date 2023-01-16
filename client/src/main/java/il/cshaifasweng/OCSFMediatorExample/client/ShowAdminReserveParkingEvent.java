package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowAdminReserveParkingEvent {
    private Object parkingLotList;
    private Object worker;
    public ShowAdminReserveParkingEvent(Message msg) {
        parkingLotList=msg.getObject();
        worker=msg.getObject2();
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
