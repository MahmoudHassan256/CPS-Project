package il.cshaifasweng.OCSFMediatorExample.entities;

public class Car {

    private int CarNumber;
    private String Type;

    public Car(int carNumber, String type) {
        CarNumber = carNumber;
        Type = type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
