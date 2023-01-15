package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

public class ShowAdminPageEvent {

    private Object worker;
    private Object complaintList;
    private  Object refundList;

    public ShowAdminPageEvent(Message msg) {
        worker=msg.getObject();
        complaintList=msg.getObject2();
        refundList=msg.getObject3();
    }

    public Object getRefundList() {
        return refundList;
    }

    public void setRefundList(Object refundList) {
        this.refundList = refundList;
    }

    public Object getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(Object complaintList) {
        this.complaintList = complaintList;
    }

    public Object getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
