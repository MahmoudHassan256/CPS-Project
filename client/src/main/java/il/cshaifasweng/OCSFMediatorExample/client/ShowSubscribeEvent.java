package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowSubscribeEvent {
    private Object parkingLotsList;
    public ShowSubscribeEvent(Message msg) {
        this.parkingLotsList = msg.getObject();
    }
    public Object getParkingLotsList() {
        return parkingLotsList;
    }

    public void setParkingLotsList(Object parkingLotsList) {
        this.parkingLotsList = parkingLotsList;
    }
}
