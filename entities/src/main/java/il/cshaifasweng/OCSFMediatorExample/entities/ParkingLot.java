package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="parking_lots")
public class ParkingLot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int capacity;
    @Column(name = "parkinglot_status",columnDefinition = "BLOB")
    private List<Object> parkingLotStatus;
    @OneToMany
    private List<Vehicle> vehicleList;
    @OneToMany
    private List<Worker> workerList;
    public ParkingLot(int floor,int row,int depth){
    this.capacity=floor*row*depth;
    Object[] temp=new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            temp[i]="Open";
        }
    parkingLotStatus= Arrays.asList(temp);
    }


    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public List<Object> getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(List<Object> parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public ParkingLot() {
    }
    public void addWorker(Worker worker){
        if(workerList==null){
            workerList=new ArrayList<Worker>();
        }
        workerList.add(worker);
    }
    public void addVehicle(Vehicle vehicle){
        if(vehicleList==null){
            vehicleList=new ArrayList<Vehicle>();
        }
        vehicleList.add(vehicle);
    }
}
