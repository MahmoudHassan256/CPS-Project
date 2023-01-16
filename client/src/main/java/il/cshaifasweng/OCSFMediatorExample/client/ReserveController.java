/**
 * Sample Skeleton for 'reserve.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import il.cshaifasweng.OCSFMediatorExample.entities.SubsriptionClient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.Arrays;
import java.util.List;


public class ReserveController {
    private  static List<ParkingLot> parkingLots;
    private static List<SubsriptionClient> subsriptionClients;

    public static List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public static void setParkingLots(List<ParkingLot> parkingLots) {
        ReserveController.parkingLots = parkingLots;
    }
    public static List<SubsriptionClient> getSubsriptionClients(){return subsriptionClients;}
    public static void setSubsriptionClients(List<SubsriptionClient> subsriptionClients){
        ReserveController.subsriptionClients= subsriptionClients;
    }

    List<String> hoursList = Arrays.asList("00","01","02","03","04","05","06","07","08",
            "09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    List <String> minutesList = Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13",
            "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33",
            "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52",
            "53","54","55","56","57","58","59");
    List<String> monthLst = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
    List<String> yearLst = Arrays.asList("23","24","25","26","27","28");
    List<String> typeList = Arrays.asList("Casual subscription single car",
            "Casual subscription, multiple cars","Full subscription");

    @FXML // fx:id="labelErrorInvalid"
    private Label labelErrorInvalid; // Value injected by FXMLLoader
    @FXML // fx:id="cbSubsType"
    private ComboBox<String> cbSubsType; // Value injected by FXMLLoader
    @FXML // fx:id="aririvalDate"
    private DatePicker aririvalDate; // Value injected  by FXMLLoader

    @FXML // fx:id="arrivalHour"
    private ComboBox<String> arrivalHour; // Value injected by FXMLLoader

    @FXML // fx:id="arrivalMinute"
    private ComboBox<String> arrivalMinute; // Value injected by FXMLLoader

    @FXML // fx:id="btnBack"
    private Button btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="btnReserve"
    private Button btnReserve; // Value injected by FXMLLoader

    @FXML // fx:id="cbOneTimer"
    private CheckBox cbOneTimer; // Value injected by FXMLLoader

    @FXML // fx:id="cbSubscriber"
    private CheckBox cbSubscriber; // Value injected by FXMLLoader

    @FXML // fx:id="departureDate"
    private DatePicker departureDate; // Value injected by FXMLLoader

    @FXML // fx:id="departureHour"
    private ComboBox<String> departureHour; // Value injected by FXMLLoader

    @FXML // fx:id="departureMinute"
    private ComboBox<String> departureMinute; // Value injected by FXMLLoader

    @FXML // fx:id="expirationMonth"
    private ComboBox<String> expirationMonth; // Value injected by FXMLLoader

    @FXML // fx:id="expirationYear"
    private ComboBox<String> expirationYear; // Value injected by FXMLLoader

    @FXML // fx:id="parkingLotComboBox"
    private ComboBox<Integer> parkingLotComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="tfCVV"
    private TextField tfCVV; // Value injected by FXMLLoader

    @FXML // fx:id="tfCardNumber"
    private TextField tfCardNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tfCardOwnerID"
    private TextField tfCardOwnerID; // Value injected by FXMLLoader

    @FXML // fx:id="tfEmail"
    private TextField tfEmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfID"
    private TextField tfID; // Value injected by FXMLLoader

    @FXML // fx:id="tfLicense"
    private TextField tfLicense; // Value injected by FXMLLoader

    @FXML // fx:id="tfSubscribtionID"
    private TextField tfSubscribtionID; // Value injected by FXMLLoader

    @FXML
    void cbOneTimerSelected(ActionEvent event) {
        if(cbOneTimer.isSelected())
        {
            cbSubsType.setDisable(true);
            cbSubscriber.setSelected(false);
            tfSubscribtionID.setDisable(true);
            tfCardNumber.setDisable(false);
            expirationMonth.setDisable(false);
            expirationYear.setDisable(false);
            tfCVV.setDisable(false);
            tfCardOwnerID.setDisable(false);
        }
        else
        {
            tfCardNumber.setDisable(true);
            expirationMonth.setDisable(true);
            expirationYear.setDisable(true);
            tfCVV.setDisable(true);
            tfCardOwnerID.setDisable(true);
        }

    }

    @FXML
    void cbSubscriberSelected(ActionEvent event) {
        if(cbSubscriber.isSelected())
        {
            cbSubsType.setDisable(false);
            tfSubscribtionID.setDisable(false);
            cbOneTimer.setSelected(false);
            tfCardNumber.setDisable(true);
            expirationMonth.setDisable(true);
            expirationYear.setDisable(true);
            tfCVV.setDisable(true);
            tfCardOwnerID.setDisable(true);
        }
        else {
            tfSubscribtionID.setDisable(true);
            cbSubsType.setDisable(true);
        }

    }

    @FXML
    void gotoprimary(ActionEvent event) {
        try {
            App.setRoot("firstscene");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void reserveParkingLot(ActionEvent event){
        labelErrorInvalid.setVisible(false);

        if(!tfID.getText().isEmpty() && !tfLicense.getText().isEmpty() && !parkingLotComboBox.getSelectionModel().isEmpty()
                && aririvalDate.getValue()!=null && !arrivalHour.getSelectionModel().isEmpty() && !arrivalMinute.getSelectionModel().isEmpty()
        && !tfEmail.getText().isEmpty() && (cbSubscriber.isSelected() || cbOneTimer.isSelected())) {
            LocalDateTime arrivalDate = LocalDateTime.of(aririvalDate.getValue().getYear(), aririvalDate.getValue().getMonth(),
                    aririvalDate.getValue().getDayOfMonth(), Integer.parseInt(arrivalHour.getValue()), Integer.parseInt(arrivalMinute.getValue()));
            LocalDateTime departureDate1;
            if (cbSubscriber.isSelected()) {
                for(SubsriptionClient subsriptionClient: subsriptionClients)
                {
                    System.out.println(subsriptionClient.getId());
                    if(subsriptionClient.getId()==Integer.parseInt(tfSubscribtionID.getText()) &&
                            subsriptionClient.getCarNumberList().contains(tfLicense.getText()) &&
                            cbSubsType.getSelectionModel().getSelectedItem().equals(subsriptionClient.SubscriptionType))
                    {
                        if(cbSubsType.getSelectionModel().getSelectedItem().startsWith("Full"))
                        {
                            departureDate1 = null;

                        }
                        else if(departureDate.getValue()!=null && !departureHour.getSelectionModel().isEmpty() && !departureMinute.getSelectionModel().isEmpty()
                                && parkingLotComboBox.getSelectionModel().getSelectedItem()== subsriptionClient.getDesiredPrkinglot())
                        {
                            departureDate1 = LocalDateTime.of(departureDate.getValue().getYear(), departureDate.getValue().getMonth(),
                                    departureDate.getValue().getDayOfMonth(), Integer.parseInt(departureHour.getValue()), Integer.parseInt(departureMinute.getValue()));
                        }
                        else {
                            labelErrorInvalid.setVisible(true);
                            break;
                        }
                        Reservation reservation = new Reservation(tfID.getText(), tfLicense.getText(),
                                parkingLotComboBox.getValue(), arrivalDate, departureDate1, tfEmail.getText(),
                                cbSubsType.getSelectionModel().getSelectedItem(), tfSubscribtionID.getText());
                        try {
                            SimpleClient.getClient().sendToServer(new Message("#AddReservationRequest", reservation));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    App.setRoot("firstscene");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },2000);

                    }
                    else
                    {
                        labelErrorInvalid.setVisible(true);
                    }
                }

            } else if (cbOneTimer.isSelected() &&departureDate.getValue()!=null && !departureHour.getSelectionModel().isEmpty()
            && !departureMinute.getSelectionModel().isEmpty() && !tfCardNumber.getText().isEmpty() && !expirationMonth.getSelectionModel().isEmpty()
            && !expirationYear.getSelectionModel().isEmpty() && !tfCVV.getText().isEmpty() && !tfCardOwnerID.getText().isEmpty()) {
                departureDate1 = LocalDateTime.of(departureDate.getValue().getYear(), departureDate.getValue().getMonth(),
                        departureDate.getValue().getDayOfMonth(), Integer.parseInt(departureHour.getValue()), Integer.parseInt(departureMinute.getValue()));
                LocalDate expirationD = LocalDate.of(Integer.parseInt(expirationYear.getValue()), Integer.parseInt(expirationMonth.getValue()), 1);
                Reservation reservation = new Reservation(tfID.getText(), tfLicense.getText(),
                        parkingLotComboBox.getValue(), arrivalDate, departureDate1, tfEmail.getText(), "one-Timer",
                        tfCardNumber.getText(), expirationD, tfCVV.getText(), tfCardOwnerID.getText());
                try {
                    SimpleClient.getClient().sendToServer(new Message("#AddReservationRequest", reservation));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            App.setRoot("firstscene");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },2000);
            }
            else {
                labelErrorInvalid.setVisible(true);
            }
        }
        else{
        labelErrorInvalid.setVisible(true);
        }
    }

    public void initialize(){
        arrivalHour.getItems().clear();
        arrivalHour.setItems(FXCollections.observableArrayList(hoursList));
        departureHour.getItems().clear();
        departureHour.setItems(FXCollections.observableArrayList(hoursList));
        arrivalMinute.getItems().clear();
        arrivalMinute.setItems(FXCollections.observableArrayList(minutesList));
        departureMinute.getItems().clear();
        departureMinute.setItems(FXCollections.observableArrayList(minutesList));
        expirationMonth.getItems().clear();
        expirationMonth.setItems(FXCollections.observableArrayList(monthLst));
        expirationYear.getItems().clear();
        expirationYear.setItems(FXCollections.observableArrayList(yearLst));
        for(ParkingLot ParkingLot: parkingLots)
        {
            parkingLotComboBox.getItems().addAll(ParkingLot.getId());
        }
        cbSubsType.getItems().clear();
        cbSubsType.setItems(FXCollections.observableArrayList(typeList));
    }
    @FXML
    void cbSubsTypeSelected(ActionEvent event) {
        if(cbSubsType.getSelectionModel().getSelectedItem().startsWith("Full"))
        {
            departureDate.setDisable(true);
            departureHour.setDisable(true);
            departureMinute.setDisable(true);
        }
        else {
            departureDate.setDisable(false);
            departureHour.setDisable(false);
            departureMinute.setDisable(false);
        }
    }
}
