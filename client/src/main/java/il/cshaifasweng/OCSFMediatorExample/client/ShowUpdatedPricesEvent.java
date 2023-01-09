package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowUpdatedPricesEvent {
    private Object pricesTable;

    public Object getPricesTable() {
        return pricesTable;
    }

    public void setPricesTable(Object pricesTable) {
        this.pricesTable = pricesTable;
    }

    public ShowUpdatedPricesEvent(Message msg) {
        this.pricesTable = msg.getObject();
    }
}
