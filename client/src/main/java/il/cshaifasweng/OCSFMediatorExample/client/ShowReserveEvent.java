package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowReserveEvent {
    private Object parkingLotsList;
    private Object subscriptionList;
    public ShowReserveEvent(Message msg) {
        this.parkingLotsList = msg.getObject();
        this.subscriptionList = msg.getObject2();
    }

    public Object getParkingLotsList() {
        return parkingLotsList;
    }
    public void setParkingLotsList(Object parkingLotsList) {
        this.parkingLotsList = parkingLotsList;
    }

    public Object getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(Object subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
