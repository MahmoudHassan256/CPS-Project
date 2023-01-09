package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;

public class Message implements Serializable{
    private String message;
    private Object object;

    public Message(String message, Object object) {
        this.message = message;
        this.object = object;
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
