package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ChangePriceChainEvent {
    private Object updatedprice;
    public ChangePriceChainEvent(Message msg) {
        updatedprice=msg.getObject();
    }

    public Object getUpdatedprice() {
        return updatedprice;
    }

    public void setUpdatedprice(Object updatedprice) {
        this.updatedprice = updatedprice;
    }
}
