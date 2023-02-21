/**
 * Sample Skeleton for 'profile.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import il.cshaifasweng.OCSFMediatorExample.entities.SubsriptionClient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {

    private static List<Reservation> reservationList;
    private static List<SubsriptionClient> subsriptionClientList;

    public static List<Reservation> getReservationList() {
        return reservationList;
    }

    public static void setReservationList(List<Reservation> reservationList) {
        ProfileController.reservationList = reservationList;
    }

    public static List<SubsriptionClient> getSubsriptionClientList() {
        return subsriptionClientList;
    }

    public static void setSubsriptionClientList(List<SubsriptionClient> subsriptionClientList) {
        ProfileController.subsriptionClientList = subsriptionClientList;
    }

    @FXML // fx:id="RemainingHours"
    private TableColumn<SubscribeController, LocalDateTime> RemainingHours; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="errormsg"
    private Text errormsg; // Value injected by FXMLLoader

    @FXML // fx:id="errormsgbox"
    private HBox errormsgbox; // Value injected by FXMLLoader

    @FXML // fx:id="id"
    private TableColumn<Reservation,Integer> id; // Value injected by FXMLLoader

    @FXML // fx:id="parkingLotID"
    private TableColumn<Reservation,String> parkingLotID; // Value injected by FXMLLoader

    @FXML // fx:id="reservationTable"
    private TableView<Reservation> reservationTable; // Value injected by FXMLLoader

    @FXML // fx:id="reservationbox"
    private HBox reservationbox; // Value injected by FXMLLoader

    @FXML // fx:id="sT_id"
    private TableColumn<SubsriptionClient,Integer> sT_id; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptionExpDate"
    private TableColumn<SubsriptionClient,LocalDateTime> subscriptionExpDate; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptionTable"
    private TableView<SubsriptionClient> subscriptionTable; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptionType"
    private TableColumn<SubsriptionClient,String> subscriptionType; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptionbox"
    private VBox subscriptionbox; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnumber"
    private TextField tfcarnumber; // Value injected by FXMLLoader

    @FXML // fx:id="timeOfArrival"
    private TableColumn<Reservation,LocalDateTime> timeOfArrival; // Value injected by FXMLLoader

    @FXML // fx:id="timeOfDeparture"
    private TableColumn<Reservation,LocalDateTime> timeOfDeparture; // Value injected by FXMLLoader

    @FXML
    void gotoprimary(ActionEvent event) {
        try {
            App.setRoot("firstscene");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updatescene(ActionEvent event) {
        if(tfcarnumber.getText().equals("")){
            reservationbox.setVisible(false);
            subscriptionbox.setVisible(false);
            errormsgbox.setVisible(false);
        }
        else{

            reservationTable.getItems().clear();
            subscriptionTable.getItems().clear();
            List<Reservation> newreservationList=new ArrayList<Reservation>();
            List<SubsriptionClient> newsubscriptionList=new ArrayList<SubsriptionClient>();
            for (Reservation reservation:reservationList){
                if(reservation.getLicensePlate().equals(tfcarnumber.getText())){
                    newreservationList.add(reservation);
                }
            }
            for (SubsriptionClient subsriptionClient:subsriptionClientList){
                if(subsriptionClient.getCarNumberList().contains(tfcarnumber.getText())){
                    newsubscriptionList.add(subsriptionClient);
                }
            }
            if(newreservationList.isEmpty() && newsubscriptionList.isEmpty()){
               errormsgbox.setVisible(true);
                subscriptionbox.setVisible(false);
                reservationbox.setVisible(false);
                errormsg.setText("Car Number isn't registered in our system!");
            }
            else if(newsubscriptionList.isEmpty()){
                errormsgbox.setVisible(false);
                subscriptionbox.setVisible(false);
                reservationbox.setVisible(true);
                reservationTable.setItems(FXCollections.observableArrayList(newreservationList));

            }
            else if(newreservationList.isEmpty()){
               errormsgbox.setVisible(false);
               reservationbox.setVisible(false);
               subscriptionbox.setVisible(true);
                subscriptionTable.setItems(FXCollections.observableArrayList(newsubscriptionList));

            }
            else{
                errormsgbox.setVisible(false);
                reservationbox.setVisible(true);
                subscriptionbox.setVisible(true);
                subscriptionTable.setItems(FXCollections.observableArrayList(newsubscriptionList));
                reservationTable.setItems(FXCollections.observableArrayList(newreservationList));
            }
            if(!newsubscriptionList.isEmpty()){
                SubsriptionClient subsriptionClient=newsubscriptionList.get(0);
                if(subsriptionClient.getSubscriptionExpDate().minusDays(7).isBefore(LocalDateTime.now())){
                    //you should resubscribe
                    errormsgbox.setVisible(true);
                    errormsg.setText("Please Renew your Subscription!!");
                }
                else{
                    errormsgbox.setVisible(false);
                }
            }
            if(!newreservationList.isEmpty()){
                for(Reservation reservation:newreservationList){
                    if(reservation.getTimeOfDeparture().isBefore(LocalDateTime.now())){
                        errormsgbox.setVisible(true);
                        errormsg.setText("one or more of your reservation has exceded the departure time.\nA fine is added");
                        break;
                    }
                    else{
                        errormsgbox.setVisible(false);
                    }
                }
            }
        }
    }
    @FXML
    void initialize(){
        reservationbox.setVisible(false);
        subscriptionbox.setVisible(false);
        errormsgbox.setVisible(false);

        sT_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        subscriptionType.setCellValueFactory(new PropertyValueFactory<>("SubscriptionType"));
        RemainingHours.setCellValueFactory(new PropertyValueFactory<>("RemainingHours"));
        subscriptionExpDate.setCellValueFactory(new PropertyValueFactory<>("subscriptionExpDate"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        parkingLotID.setCellValueFactory(new PropertyValueFactory<>("parkingLotID"));
        timeOfArrival.setCellValueFactory(new PropertyValueFactory<>("timeOfArrival"));
        timeOfDeparture.setCellValueFactory(new PropertyValueFactory<>("timeOfDeparture"));
    }
}
