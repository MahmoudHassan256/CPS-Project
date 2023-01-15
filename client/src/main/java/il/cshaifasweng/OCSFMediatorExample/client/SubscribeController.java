/**
 * Sample Skeleton for 'subscribe.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SubscribeController {
    private  static List<ParkingLot> parkingLots;

    public static List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public static void setParkingLots(List<ParkingLot> parkingLots) {
        SubscribeController.parkingLots = parkingLots;
    }
    List<String> subscriptionTypeList = Arrays.asList("Casual subscription single car",
            "Casual subscription, multiple cars","Full subscription");
    List<String> hoursList = Arrays.asList("00","01","02","03","04","05","06","07","08",
            "09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    List<String> minutesList = Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13",
            "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33",
            "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52",
            "53","54","55","56","57","58","59");
    List<String> numberOfCarsList = Arrays.asList("1","2","3","4","5");
    List<String> monthLst = Arrays.asList("01","02","03","04,","05","06","07","08,","09","10","11","12");
    List<String> yearLst = Arrays.asList("23","24","25","26","27","28");
    @FXML // fx:id="btnBack"
    private Button btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="btnSubscribe"
    private Button btnSubscribe; // Value injected by FXMLLoader

    @FXML // fx:id="cbDepartureHour"
    private ComboBox<String> cbDepartureHour; // Value injected by FXMLLoader

    @FXML // fx:id="cbDepartureMinute"
    private ComboBox<String> cbDepartureMinute; // Value injected by FXMLLoader

    @FXML // fx:id="cbExpirationMonth"
    private ComboBox<String> cbExpirationMonth; // Value injected by FXMLLoader

    @FXML // fx:id="cbExpirationYear"
    private ComboBox<String> cbExpirationYear; // Value injected by FXMLLoader

    @FXML // fx:id="cbParkingLotID"
    private ComboBox<Integer> cbParkingLotID; // Value injected by FXMLLoader

    @FXML // fx:id="cbSubscriptionType"
    private ComboBox<String> cbSubscriptionType; // Value injected by FXMLLoader

    @FXML // fx:id="scrollPane"
    private VBox scrollPane; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptionStartingDate"
    private DatePicker subscriptionStartingDate; // Value injected by FXMLLoader
    @FXML // fx:id="btnOkCars"
    private Button btnOkCars; // Value injected by FXMLLoader

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

    @FXML // fx:id="tfNumberOfCars"
    private TextField tfNumberOfCars; // Value injected by FXMLLoader

    @FXML
    void addSubscription(ActionEvent event) {

    }

    @FXML
    void addedNumberOfCars(ActionEvent event) {
        scrollPane.getChildren().clear();
    if(StringUtils.isNumeric(tfNumberOfCars.getText() ))
    {
        Platform.runLater(() -> {
            ScrollPane sp = new ScrollPane();
            VBox vBox = new VBox();
            sp.setContent(vBox);
            for (int i=1; i <= Integer.parseInt(tfNumberOfCars.getText()); i++)
            {

                Label label= new Label("Enter car number #"+i);
                TextField textField=new TextField();
                textField.setId("tfCarNumber"+i);
                HBox hBox=new HBox();
                hBox.getChildren().addAll(label,textField);
                vBox.getChildren().addAll(hBox);
            }
            scrollPane.getChildren().addAll(sp);

        });


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
    public void initialize(){
        cbSubscriptionType.getItems().clear();
        cbSubscriptionType.setItems(FXCollections.observableArrayList(subscriptionTypeList));
        cbParkingLotID.getItems().clear();
        for(ParkingLot ParkingLot: parkingLots)
        {
            cbParkingLotID.getItems().addAll(ParkingLot.getId());
        }
        cbDepartureHour.getItems().clear();
        cbDepartureHour.setItems(FXCollections.observableArrayList(hoursList));
        cbDepartureMinute.getItems().clear();
        cbDepartureMinute.setItems(FXCollections.observableArrayList(minutesList));
        cbExpirationMonth.getItems().clear();
        cbExpirationMonth.setItems(FXCollections.observableArrayList(monthLst));
        cbExpirationYear.getItems().clear();
        cbExpirationYear.setItems(FXCollections.observableArrayList(yearLst));
    }
}
