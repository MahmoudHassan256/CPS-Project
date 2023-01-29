package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class Message implements Serializable{
    private String message;
    private Object object;
    private Object object2;
    private Object object3;
    private Object object4;


    public Message(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public Message(String message, Object object, Object object2) {
        this.message = message;
        this.object = object;
        this.object2 = object2;
    }

    public Message(String message, Object object, Object object2, Object object3) {
        this.message = message;
        this.object = object;
        this.object2 = object2;
        this.object3 = object3;
    }
    public Message(String message, Object object, Object object2, Object object3, Object object4) {
        this.message = message;
        this.object = object;
        this.object2 = object2;
        this.object3 = object3;
        this.object4=object4;
    }

    public Object getObject4() {
        return object4;
    }

    public void setObject4(Object object4) {
        this.object4 = object4;
    }

    public Object getObject2() {
        return object2;
    }

    public Object getObject3() {
        return object3;
    }

    public void setObject3(Object object3) {
        this.object3 = object3;
    }

    public void setObject2(Object object2) {
        this.object2 = object2;
    }

    public Message(String message) {
        this.message = message;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    @Override
    public String toString() {
        return this.message;
    }
}
