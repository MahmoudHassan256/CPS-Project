package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import il.cshaifasweng.OCSFMediatorExample.entities.SubsriptionClient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CancelReservationController {
    private static List<Reservation> reservation;
    private static List<SubsriptionClient> subsriptionClients;
    private static List<Price> prices;

    public static List<SubsriptionClient> getSubsriptionClients() {
        return subsriptionClients;
    }

    public static void setSubsriptionClients(List<SubsriptionClient> subsriptionClients) {
        CancelReservationController.subsriptionClients = subsriptionClients;
    }

    public static List<Price> getPrices() {
        return prices;
    }

    public static void setPrices(List<Price> prices) {
        CancelReservationController.prices = prices;
    }

    public static Reservation chosenReservation = null;
    public static List<Reservation> getReservation() {
        return reservation;
    }

    public static void setReservation(List<Reservation> reservation) {
        CancelReservationController.reservation = reservation;
    }


    @FXML // fx:id="arrivalTableColumn"
    private TableColumn<Reservation, LocalDateTime> arrivalTableColumn; // Value injected by FXMLLoader

    @FXML // fx:id="departureTableColumn"
    private TableColumn<Reservation, LocalDateTime> departureTableColumn; // Value injected by FXMLLoader
    @FXML // fx:id="driverIDTableColumn"
    private TableColumn<Reservation, String> driverIDTableColumn; // Value injected by FXMLLoader
    @FXML // fx:id="idTableColumn"
    private TableColumn<Reservation, Integer> idTableColumn; // Value injected by FXMLLoader
    @FXML // fx:id="licensePlateTableColumn"
    private TableColumn<Reservation, String> licensePlateTableColumn; // Value injected by FXMLLoader

    @FXML // fx:id="parkingLotIDTableColumn"
    private TableColumn<Reservation, Integer> parkingLotIDTableColumn; // Value injected by FXMLLoader
    @FXML // fx:id="confirmBtn"
    private Button confirmBtn; // Value injected by FXMLLoader
    @FXML // fx:id="labelReservationCanceled"
    private Label labelReservationCanceled; // Value injected by FXMLLoader
    @FXML // fx:id="labelErrorInput"
    private Label labelErrorInput; // Value injected by FXMLLoader

    @FXML // fx:id="labelNoReservation"
    private Label labelNoReservation; // Value injected by FXMLLoader
    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cancelReservationBtn"
    private Button cancelReservationBtn; // Value injected by FXMLLoader

    @FXML // fx:id="tfLicensePlate"
    private TextField tfLicensePlate; // Value injected by FXMLLoader
    @FXML // fx:id="reservationTable"
    private TableView<Reservation> reservationTable; // Value injected by FXMLLoader
    @FXML // fx:id="labelPayment"
    private Label labelPayment; // Value injected by FXMLLoader

    @FXML
    void confirmBtnPressed(ActionEvent event) {
        List<Reservation> reservation2 = new ArrayList<Reservation>();
        labelErrorInput.setVisible(false);
        labelNoReservation.setVisible(false);
        if (!tfLicensePlate.getText().isEmpty() && StringUtils.isNumeric(tfLicensePlate.getText())) {
            for (Reservation reservation1 : reservation) {
                if (reservation1.getLicensePlate().contains(tfLicensePlate.getText())) {
                    reservation2.add(reservation1);
                }
            }
            if (reservation2.isEmpty()) {
                labelNoReservation.setVisible(true);
            } else {
                reservationTable.setItems(FXCollections.observableArrayList(reservation2));
                reservationTable.setVisible(true);
            }

        }
        else{
            labelErrorInput.setVisible(true);
        }
    }
    @FXML
    void getReservationInfo(MouseEvent event) {
        chosenReservation = reservationTable.getItems().get(reservationTable.getSelectionModel().getSelectedIndex());

    }
    @FXML
    void cancelReservationPressed(ActionEvent event){
        if(chosenReservation != null){
            SubsriptionClient subsriptionClient1 = null;
            LocalDateTime time = LocalDateTime.now();
            Duration timeElapsed = Duration.between(time,chosenReservation.getTimeOfArrival());
            Duration howLong = Duration.between(chosenReservation.getTimeOfArrival(), chosenReservation.getTimeOfDeparture());
            if(!chosenReservation.getSubsriptionID().isEmpty())
            {
                for(SubsriptionClient subsriptionClient: subsriptionClients)
                {
                    if(subsriptionClient.getCarNumberList().contains(chosenReservation.getLicensePlate()))
                    {
                        subsriptionClient1=subsriptionClient;
                        if(timeElapsed.toHours()>=3)
                        {
                            subsriptionClient1.setRemainingHours((int)(subsriptionClient.getRemainingHours()- 0.1*howLong.toHours()));

                        }
                        else if(timeElapsed.toHours()<3 && timeElapsed.toHours()>=1)
                        {
                            subsriptionClient1.setRemainingHours((int)(subsriptionClient.getRemainingHours()- 0.5*howLong.toHours()));
                        }
                        else
                            subsriptionClient1.setRemainingHours((int)(subsriptionClient.getRemainingHours()- howLong.toHours()));

                        labelPayment.setText("You have "+subsriptionClient1.getRemainingHours()+" remaining hours");
                        labelPayment.setVisible(true);
                    }
                }
            }
            else{
                int perhour = 0;
                double payment;
                for(Price price: prices)
                {
                    if(price.getParkingType().startsWith("Requested One"))
                    {
                        perhour = Integer.parseInt(price.getPrice());
                    }
                }
                if(timeElapsed.toHours()>=3)
                {
                    payment = perhour*howLong.toHours()*0.1;
                }
                else if(timeElapsed.toHours()<3 && timeElapsed.toHours()>=1)
                {
                    payment = perhour*howLong.toHours()*0.5;
                }
                else{
                    payment = perhour*howLong.toHours();
                }
                labelPayment.setText("you were charged "+ payment);
                labelPayment.setVisible(true);
            }
            try {
                SimpleClient.getClient().sendToServer(new Message("#CancelReservationRequest", chosenReservation, subsriptionClient1));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            labelReservationCanceled.setVisible(true);
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

            labelNoReservation.setVisible(true);
        }
            }
    @FXML
    void gotoprimary(ActionEvent event){
        try {
            App.setRoot("firstscene");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize() {
        reservationTable.setVisible(false);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
        driverIDTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("driverID"));
        licensePlateTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("licensePlate"));
        parkingLotIDTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("parkingLotID"));
        arrivalTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation,LocalDateTime>("timeOfArrival"));
        departureTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation,LocalDateTime>("timeOfDeparture"));

    }


}
