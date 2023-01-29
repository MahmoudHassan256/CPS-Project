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
    @ElementCollection(targetClass = Spot.class,fetch = FetchType.EAGER)
    private List<Spot> parkingLotStatus;
    @OneToMany
    private List<Worker> workerList;
    public ParkingLot(int floor,int row,int depth){
    this.capacity=floor*row*depth;
        Spot[] temp=new Spot[capacity];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < floor; j++) {
                for (int k = 0; k < row; k++) {
                    temp[(i*row*floor + j*row +k)]=new Spot("Open",k,j,i);;
                }
            }
        }
    parkingLotStatus= Arrays.asList(temp);
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

    public List<Spot> getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(List<Spot> parkingLotStatus) {
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
}
