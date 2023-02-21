/**
 * Sample Skeleton for 'reports.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ReportsController {

    private static List<Complaint> complaintList;
    private static List<Reservation> reservationList;
    private static List<ParkingLot> parkingLotList;
    private static Worker worker;

    public static List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public static void setParkingLotList(List<ParkingLot> parkingLotList) {
        ReportsController.parkingLotList = parkingLotList;
    }

    public static List<Complaint> getComplaintList() {
        return complaintList;
    }

    public static void setComplaintList(List<Complaint> complaintList) {
        ReportsController.complaintList = complaintList;
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }

    public static void setReservationList(List<Reservation> reservationList) {
        ReportsController.reservationList = reservationList;
    }

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        ReportsController.worker = worker;
    }

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader
    /*Complaint Table*/
    @FXML // fx:id="complaintTable"
    private TableView<Complaint> complaintTable; // Value injected by FXMLLoader
    @FXML // fx:id="cT_id"
    private TableColumn<Complaint,Integer> cT_id; // Value injected by FXMLLoader
    @FXML // fx:id="carNumber"
    private TableColumn<Complaint,String> carNumber; // Value injected by FXMLLoader
    @FXML // fx:id="description"
    private TableColumn<Complaint,String> description; // Value injected by FXMLLoader
    @FXML // fx:id="parkinglotTable"
    private TableView<ParkingLot> parkinglotTable; // Value injected by FXMLLoader

    @FXML // fx:id="plT_id"
    private TableColumn<ParkingLot,Integer> plT_id; // Value injected by FXMLLoader

    @FXML // fx:id="plT_capacity"
    private TableColumn<ParkingLot,Integer> plT_capacity; // Value injected by FXMLLoader
    @FXML // fx:id="refundValue"
    private TableColumn<Complaint,String> refundValue; // Value injected by FXMLLoader
    @FXML // fx:id="response"
    private TableColumn<Complaint,String> response; // Value injected by FXMLLoader
    @FXML // fx:id="status"
    private TableColumn<Complaint,String> status; // Value injected by FXMLLoader
    @FXML // fx:id="submitionDate"
    private TableColumn<Complaint, LocalDateTime> submitionDate; // Value injected by FXMLLoader


    /*Reservation Table*/
    @FXML // fx:id="reservationTable"
    private TableView<Reservation> reservationTable; // Value injected by FXMLLoader
    @FXML // fx:id="rT_id"
    private TableColumn<Reservation,Integer> rT_id; // Value injected by FXMLLoader
    @FXML // fx:id="creditCardHolderID"
    private TableColumn<Reservation,String> creditCardHolderID; // Value injected by FXMLLoader
    @FXML // fx:id="creditCardNumber"
    private TableColumn<Reservation,String> creditCardNumber; // Value injected by FXMLLoader
    @FXML // fx:id="cvv"
    private TableColumn<Reservation,String> cvv; // Value injected by FXMLLoader
    @FXML // fx:id="driverID"
    private TableColumn<Reservation,String> driverID; // Value injected by FXMLLoader
    @FXML // fx:id="email"
    private TableColumn<Reservation,String> email; // Value injected by FXMLLoader
    @FXML // fx:id="expirationDate"
    private TableColumn<Reservation, Date> expirationDate; // Value injected by FXMLLoader
    @FXML // fx:id="licensePlate"
    private TableColumn<Reservation,?> licensePlate; // Value injected by FXMLLoader
    @FXML // fx:id="parkingLotID"
    private TableColumn<Reservation,Integer> parkingLotID; // Value injected by FXMLLoader
    @FXML // fx:id="subsriptionID"
    private TableColumn<Reservation,String> subsriptionID; // Value injected by FXMLLoader
    @FXML // fx:id="timeOfArrival"
    private TableColumn<Reservation,LocalDateTime> timeOfArrival; // Value injected by FXMLLoader
    @FXML // fx:id="timeOfDeparture"
    private TableColumn<Reservation,LocalDateTime> timeOfDeparture; // Value injected by FXMLLoader
    @FXML // fx:id="typeOfClient"
    private TableColumn<Reservation,String> typeOfClient; // Value injected by FXMLLoader


    @FXML
    void goback(ActionEvent event) {
        try {
            App.setRoot("chainmanagerpage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshComplaintListEvent(RefreshComplaintListEvent event){
        complaintTable.getItems().clear();
        setComplaintList((List<Complaint>) event.getComplaintList());
        List<Complaint> newcomplaintList= (List<Complaint>) event.getComplaintList();
        complaintTable.setItems(FXCollections.observableArrayList(newcomplaintList));
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshReservationListEvent(RefreshReservationListEvent event){
        reservationTable.getItems().clear();
        setReservationList((List<Reservation>) event.getReservationList());
        List<Reservation> newreservationList= (List<Reservation>) event.getReservationList();
        reservationTable.setItems(FXCollections.observableArrayList(newreservationList));
    }
    @FXML
    void initialize(){
        EventBus.getDefault().register(this);

        rT_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        creditCardHolderID.setCellValueFactory(new PropertyValueFactory<>("creditCardHolderID"));
        creditCardNumber.setCellValueFactory(new PropertyValueFactory<>("creditCardNumber"));
        cvv.setCellValueFactory(new PropertyValueFactory<>("cvv"));
        driverID.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        expirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        licensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        parkingLotID.setCellValueFactory(new PropertyValueFactory<>("parkingLotID"));
        subsriptionID.setCellValueFactory(new PropertyValueFactory<>("subsriptionID"));
        timeOfArrival.setCellValueFactory(new PropertyValueFactory<>("timeOfArrival"));
        timeOfDeparture.setCellValueFactory(new PropertyValueFactory<>("timeOfDeparture"));
        typeOfClient.setCellValueFactory(new PropertyValueFactory<>("typeOfClient"));


        cT_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        carNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        refundValue.setCellValueFactory(new PropertyValueFactory<>("refundValue"));
        response.setCellValueFactory(new PropertyValueFactory<>("response"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        submitionDate.setCellValueFactory(new PropertyValueFactory<>("submitionDate"));


        plT_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        plT_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        parkinglotTable.setItems(FXCollections.observableArrayList(parkingLotList));
        reservationTable.setItems(FXCollections.observableArrayList(reservationList));
        complaintTable.setItems(FXCollections.observableArrayList(complaintList));

    }

}
