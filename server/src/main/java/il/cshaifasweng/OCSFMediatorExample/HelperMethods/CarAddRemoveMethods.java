package il.cshaifasweng.OCSFMediatorExample.HelperMethods;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import il.cshaifasweng.OCSFMediatorExample.entities.Spot;
import il.cshaifasweng.OCSFMediatorExample.entities.Vehicle;

import java.util.Collections;
import java.util.List;

public class CarAddRemoveMethods {
    public void addReservationToPl(Reservation reservation,List<ParkingLot> parkingLotList){
        int parkingLotIndex=0;
        for (ParkingLot parkingLot:parkingLotList){
            if(parkingLot.getId()==reservation.getParkingLotID()){
             parkingLotIndex=parkingLotList.indexOf(parkingLot);
            }
        }
        List<Spot> spots=parkingLotList.get(parkingLotIndex).getParkingLotStatus();
        Vehicle car=new Vehicle(reservation.getDriverID(), reservation.getLicensePlate(), reservation.getParkingLotID(),reservation.getTimeOfArrival(),
                reservation.getTimeOfDeparture(),reservation.getTypeOfClient());
        Collections.sort(spots);
        for (Spot spot : spots) {
            if(spot.getCar().equals("open")){
                spot.setCar(car);
                break;
            }else if(spot.getCar().getClass()==Vehicle.class && ((Vehicle)spot.getCar()).getTimeOfDeparture().isAfter(car.getTimeOfDeparture())){
                Vehicle temp=(Vehicle)spot.getCar();
                spot.setCar(car);
                car=temp;
            }
        }
        sortByDepartureTime(spots);
    }
    public void removeReservationFromPl(Vehicle vehicle,List<ParkingLot> parkingLotList){
        int parkingLotIndex=0;
        for (ParkingLot parkingLot:parkingLotList){
            if(parkingLot.getId()==vehicle.getParkingLotID()){
                parkingLotIndex=parkingLotList.indexOf(parkingLot);
            }
        }
        List<Spot> spots=parkingLotList.get(parkingLotIndex).getParkingLotStatus();
        for (Spot spot:spots){
            if(spot.getCar()==vehicle){
                spot.setCar("open");
                break;
            }
        }
        sortByDepartureTime(spots);
        Collections.sort(spots);
    }

    public void sortByDepartureTime(List<Spot> spots){
        spots.sort((sp1,sp2)->{
            if(sp1.getCar().equals("open")){
                return 1;
            }else if(sp2.getCar().equals("open")){
                return -1;
            }else if(sp1.getCar().equals("damaged")|| sp2.getCar().equals("damaged")){
                return 0;
            }
            else{
                return (((Vehicle)sp1.getCar()).getTimeOfDeparture()).compareTo(((Vehicle)sp2.getCar()).getTimeOfDeparture());
            }
        });
    }
}
