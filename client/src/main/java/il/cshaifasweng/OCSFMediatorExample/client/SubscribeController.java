/**
 * Sample Skeleton for 'subscribe.fxml' Controller Class
 */

//need to check how to add the cars list

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.SubsriptionClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    @FXML
    private Label labelErrorInfo;
    @FXML
    private Label labelSubscriptionID;

    @FXML // fx:id="labelNumberError"
    private Label labelNumberError; // Value injected by FXMLLoader

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
        labelErrorInfo.setVisible(false);
        if(!tfID.getText().isEmpty() && !cbSubscriptionType.getSelectionModel().isEmpty() && subscriptionStartingDate.getValue()!= null)
        {
            if(cbSubscriptionType.getSelectionModel().getSelectedItem().startsWith("Casual subscription single car"))
            {
                if(!cbParkingLotID.getSelectionModel().isEmpty() && !cbDepartureHour.getSelectionModel().isEmpty()
                && !cbDepartureMinute.getSelectionModel().isEmpty() && !tfCardNumber.getText().isEmpty()
                && !cbExpirationYear.getSelectionModel().isEmpty() && !cbExpirationMonth.getSelectionModel().isEmpty()
                && !tfCVV.getText().isEmpty() && !tfCardOwnerID.getText().isEmpty() && !tfEmail.getText().isEmpty() && tfNumberOfCars.getText().contentEquals("1"))
                {
                    LocalDateTime startDate = LocalDateTime.of(subscriptionStartingDate.getValue().getYear(), subscriptionStartingDate.getValue().getMonth(),
                            subscriptionStartingDate.getValue().getDayOfMonth(), 0,0);
                    LocalTime departureTime = LocalTime.of(Integer.parseInt(cbDepartureHour.getValue()),Integer.parseInt(cbDepartureMinute.getValue()));
                    LocalDateTime cardExpirationDate = LocalDateTime.of(Integer.parseInt(cbExpirationYear.getValue()), Integer.parseInt(cbExpirationMonth.getValue()), 1,
                            0,0);
                    List<String> carNumberList =new ArrayList<>();
                    List<Node> nodes = getAllNodes(scrollPane);
                    for(Node node : nodes)
                    {
                        if(node instanceof TextField) {
                            carNumberList.add(((TextField) node).getText());
                        }
                    }
                    SubsriptionClient subsriptionClient = new SubsriptionClient(tfID.getText(), cbSubscriptionType.getValue(),
                            cbParkingLotID.getValue(), startDate, departureTime, 1,carNumberList,
                            tfCardNumber.getText(), cardExpirationDate, tfCVV.getText(),
                            tfCardOwnerID.getText(), tfEmail.getText(), 60);
                    labelSubscriptionID.setText("Welcome, you subscription ID is"+subsriptionClient.getId());
                    labelSubscriptionID.setVisible(true);
                    try {
                        SimpleClient.getClient().sendToServer(new Message("#AddSubscriberRequest", subsriptionClient));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                else
                    labelErrorInfo.setVisible(true);
            }
            else if(cbSubscriptionType.getSelectionModel().getSelectedItem().startsWith("Casual subscription, multiple cars"))
            {
                if(!cbParkingLotID.getSelectionModel().isEmpty() && !cbDepartureHour.getSelectionModel().isEmpty()
                        && !cbDepartureMinute.getSelectionModel().isEmpty() && !tfCardNumber.getText().isEmpty()
                        && !cbExpirationYear.getSelectionModel().isEmpty() && !cbExpirationMonth.getSelectionModel().isEmpty()
                        && !tfCVV.getText().isEmpty() && !tfCardOwnerID.getText().isEmpty() && !tfEmail.getText().isEmpty()
                        && Integer.parseInt(tfNumberOfCars.getText())>1)
                {
                    LocalDateTime startDate = LocalDateTime.of(subscriptionStartingDate.getValue().getYear(), subscriptionStartingDate.getValue().getMonth(),
                            subscriptionStartingDate.getValue().getDayOfMonth(), 0,0);
                    LocalTime departureTime = LocalTime.of(Integer.parseInt(cbDepartureHour.getValue()),Integer.parseInt(cbDepartureMinute.getValue()));
                    LocalDateTime cardExpirationDate = LocalDateTime.of(Integer.parseInt(cbExpirationYear.getValue()), Integer.parseInt(cbExpirationMonth.getValue()), 1,
                            0,0);
                    List<String> carNumberList =new ArrayList<>();
                    List<Node> nodes = getAllNodes(scrollPane);
                    for(Node node : nodes)
                    {
                        if(node instanceof TextField) {
                            carNumberList.add(((TextField) node).getText());
                        }
                    }
                    SubsriptionClient subsriptionClient = new SubsriptionClient(tfID.getText(), cbSubscriptionType.getValue(),
                            cbParkingLotID.getValue(), startDate, departureTime, Integer.parseInt(tfNumberOfCars.getText()),carNumberList,
                            tfCardNumber.getText(), cardExpirationDate, tfCVV.getText(),
                            tfCardOwnerID.getText(), tfEmail.getText(), 54*Integer.parseInt(tfNumberOfCars.getText()));
                    labelSubscriptionID.setText("Welcome, you subscription ID is"+subsriptionClient.getId());
                    labelSubscriptionID.setVisible(true);
                    try {
                        SimpleClient.getClient().sendToServer(new Message("#AddSubscriberRequest", subsriptionClient));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                    labelErrorInfo.setVisible(true);
            }
            else if(cbSubscriptionType.getSelectionModel().getSelectedItem().startsWith("Full subscription"))
            {
                if(!tfCardNumber.getText().isEmpty() && !cbExpirationYear.getSelectionModel().isEmpty() &&
                        !cbExpirationMonth.getSelectionModel().isEmpty() && !tfCVV.getText().isEmpty() &&
                        !tfCardOwnerID.getText().isEmpty() && !tfEmail.getText().isEmpty() && tfNumberOfCars.getText().contentEquals("1"))
                {
                    LocalDateTime startDate = LocalDateTime.of(subscriptionStartingDate.getValue().getYear(), subscriptionStartingDate.getValue().getMonth(),
                            subscriptionStartingDate.getValue().getDayOfMonth(), 0,0);
                    LocalDateTime cardExpirationDate = LocalDateTime.of(Integer.parseInt(cbExpirationYear.getValue()), Integer.parseInt(cbExpirationMonth.getValue()), 1,
                            0,0);
                    List<String> carNumberList =new ArrayList<>();
                    List<Node> nodes = getAllNodes(scrollPane);
                    for(Node node : nodes)
                    {
                        if(node instanceof TextField) {
                            carNumberList.add(((TextField) node).getText());
                        }
                    }
                    SubsriptionClient subsriptionClient = new SubsriptionClient(tfID.getText(), cbSubscriptionType.getValue(),
                            -1, startDate, null , Integer.parseInt(tfNumberOfCars.getText()),carNumberList,
                            tfCardNumber.getText(), cardExpirationDate, tfCVV.getText(),
                            tfCardOwnerID.getText(), tfEmail.getText(), 72);
                    labelSubscriptionID.setText("Welcome, you subscription ID is " +subsriptionClient.getId());
                    labelSubscriptionID.setVisible(true);
                    try {
                        SimpleClient.getClient().sendToServer(new Message("#AddSubscriberRequest", subsriptionClient));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                    labelErrorInfo.setVisible(true);
            }

        }
        else
            labelErrorInfo.setVisible(true);

    }

    @FXML
    void addedNumberOfCars(ActionEvent event) {
        scrollPane.getChildren().clear();
    if(StringUtils.isNumeric(tfNumberOfCars.getText() ))
    {
        labelNumberError.setVisible(false);
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
    else{
        labelNumberError.setVisible(true);
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
    @FXML
    void subscriptionTypeSelected(ActionEvent event) {
        if(cbSubscriptionType.getSelectionModel().getSelectedItem() == "Full subscription")
        {
            cbParkingLotID.setDisable(true);
            cbDepartureHour.setDisable(true);
            cbDepartureMinute.setDisable(true);
        }
        else
        {
            cbParkingLotID.setDisable(false);
            cbDepartureHour.setDisable(false);
            cbDepartureMinute.setDisable(false);
        }
    }
    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
}
