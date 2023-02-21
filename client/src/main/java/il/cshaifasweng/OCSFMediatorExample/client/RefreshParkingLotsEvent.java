package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class RefreshParkingLotsEvent {
    private Object parkingLotList;
    public RefreshParkingLotsEvent(Message msg) {
        parkingLotList=msg.getObject();
    }

    public Object getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(Object parkingLotList) {
        this.parkingLotList = parkingLotList;
    }
}
