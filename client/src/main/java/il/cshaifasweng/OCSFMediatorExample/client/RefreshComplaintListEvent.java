package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class RefreshComplaintListEvent {
    public Object complaintList;
    public RefreshComplaintListEvent(Message msg) {
        this.complaintList=msg.getObject();
    }

    public Object getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(Object complaintList) {
        this.complaintList = complaintList;
    }
}
