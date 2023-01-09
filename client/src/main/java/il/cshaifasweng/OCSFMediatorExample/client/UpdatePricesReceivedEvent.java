package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class UpdatePricesReceivedEvent {
    private Object pricestable;

    public UpdatePricesReceivedEvent(Message msg) {
        this.pricestable = msg.getObject();
    }


    public Object getPricestable() {
        return pricestable;
    }
}
