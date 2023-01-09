package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class PricesReceivedEvent {
    private Object pricesTable;

    public PricesReceivedEvent(Message msg) {
        this.pricesTable = msg.getObject();
    }

    public Object getPricesTable() {
        return pricesTable;
    }

    public void setPricesTable(Object pricesTable) {
        this.pricesTable = pricesTable;
    }
}
