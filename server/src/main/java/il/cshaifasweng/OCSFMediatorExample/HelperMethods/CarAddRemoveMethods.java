package il.cshaifasweng.OCSFMediatorExample.HelperMethods;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import il.cshaifasweng.OCSFMediatorExample.entities.Spot;
import il.cshaifasweng.OCSFMediatorExample.entities.Vehicle;

import java.util.Collections;
import java.util.List;

public class CarAddRemoveMethods {
    public static void addReservationToPl(Reservation reservation,List<ParkingLot> parkingLotList,List<Vehicle> vehicleList){
        int parkingLotIndex=0;
        for (ParkingLot parkingLot:parkingLotList){
            if(parkingLot.getId()==reservation.getParkingLotID()){
             parkingLotIndex=parkingLotList.indexOf(parkingLot);
            }
        }
        List<Spot> spots=parkingLotList.get(parkingLotIndex).getParkingLotStatus();
        Vehicle car=new Vehicle(reservation.getDriverID(), reservation.getLicensePlate(), reservation.getParkingLotID(),reservation.getTimeOfArrival(),
                reservation.getTimeOfDeparture(),reservation.getTypeOfClient());
        vehicleList.add(car);
        Collections.sort(spots);
        for (Spot spot : spots) {
            if(spot.isOpen()){
                spot.setCar(car);
                break;
            }else if(spot.getCar().getClass()==Vehicle.class && ((Vehicle)spot.getCar()).getTimeOfDeparture().isAfter(car.getTimeOfDeparture())){
                Vehicle temp=(Vehicle)spot.getCar();
                spot.setCar(car);
                car=temp;
            }
        }
        //sortByDepartureTime(spots);
    }
    public static void removeReservationFromPl(Vehicle vehicle,List<ParkingLot> parkingLotList,List<Vehicle> vehicleList){
        int parkingLotIndex=0;
        for (ParkingLot parkingLot:parkingLotList){
            if(parkingLot.getId()==vehicle.getParkingLotID()){
                parkingLotIndex=parkingLotList.indexOf(parkingLot);
            }
        }
        List<Spot> spots=parkingLotList.get(parkingLotIndex).getParkingLotStatus();
        for (Spot spot:spots){
            Vehicle car=null;
            if(spot.getCar().getClass().equals(Vehicle.class)){
                car= (Vehicle) spot.getCar();
            }
            if(car!=null){
                spot.setCar("Open");
                vehicleList.remove(vehicle);
                break;
            }
        }
        //sortByDepartureTime(spots);
        Collections.sort(spots);
    }

//    public static void sortByDepartureTime(List<Spot> spots){
//        spots.sort((sp1,sp2)->{
//            if(sp1.getCar().equals("Open")){
//                return 1;
//            }else if(sp2.getCar().equals("Open")){
//                return -1;
//            }else if(sp1.getCar().equals("damaged")|| sp2.getCar().equals("damaged")){
//                return 0;
//            }else{
//                return (((Vehicle)sp1.getCar()).getTimeOfDeparture()).compareTo(((Vehicle)sp2.getCar()).getTimeOfDeparture());
//            }
//        });
//    }
}
