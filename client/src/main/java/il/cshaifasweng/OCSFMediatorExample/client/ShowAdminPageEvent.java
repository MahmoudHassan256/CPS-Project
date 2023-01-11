package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

public class ShowAdminPageEvent {

    private Object worker;

    public ShowAdminPageEvent(Message msg) {
        worker=msg.getObject();
    }

    public Object getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
