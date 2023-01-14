package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowReserveEvent {
    private Object parkingLotsList;
    public ShowReserveEvent(Message msg) {
        this.parkingLotsList = msg.getObject();
    }

    public Object getParkingLotsList() {
        return parkingLotsList;
    }

    public void setParkingLotsList(Object parkingLotsList) {
        this.parkingLotsList = parkingLotsList;
    }
}
