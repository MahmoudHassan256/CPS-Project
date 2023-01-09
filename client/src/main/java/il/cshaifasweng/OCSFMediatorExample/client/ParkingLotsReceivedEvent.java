package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ParkingLotsReceivedEvent {
    private Object parkinglotsTable;

    public ParkingLotsReceivedEvent(Message msg) {
        this.parkinglotsTable = msg.getObject();
    }

    public Object getParkinglotsTable() {
        return parkinglotsTable;
    }
}
