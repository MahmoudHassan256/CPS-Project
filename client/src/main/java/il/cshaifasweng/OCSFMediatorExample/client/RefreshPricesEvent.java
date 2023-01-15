package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class RefreshPricesEvent {
    private Object pricesList;
    public RefreshPricesEvent(Message msg) {
        pricesList=msg.getObject();
    }

    public Object getPricesList() {
        return pricesList;
    }

    public void setPricesList(Object pricesList) {
        this.pricesList = pricesList;
    }
}
