package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowSubscriptionIDEvent {
    private Object id;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public ShowSubscriptionIDEvent(Message msg) {
        id = msg.getObject();
    }
}
