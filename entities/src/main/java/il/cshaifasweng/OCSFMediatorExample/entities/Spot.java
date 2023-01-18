package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;


public class Spot implements Serializable {

    private Object car;
    private int rowNum;
    private int floorNum;
    private int depthNum;

    public Spot(Object car, int rowNum, int floorNum, int depthNum) {
        this.car = car;
        this.rowNum = rowNum;
        this.floorNum = floorNum;
        this.depthNum = depthNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public int getDepthNum() {
        return depthNum;
    }

    public void setDepthNum(int depthNum) {
        this.depthNum = depthNum;
    }

    public Spot() {

    }
    public Object getCar() {
        return car;
    }

    public void setCar(Object car) {
        this.car = car;
    }

}
