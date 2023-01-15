package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ShowSignInEvent {
    private Object workersTable;
    public ShowSignInEvent(Message msg) {
        this.workersTable=msg.getObject();
    }


    public Object getWorkersTable() {
        return workersTable;
    }

    public void setWorkersTable(Object workersTable) {
        this.workersTable = workersTable;
    }
}
