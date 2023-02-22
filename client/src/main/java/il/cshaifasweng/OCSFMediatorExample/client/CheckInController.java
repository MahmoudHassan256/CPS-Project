

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class CheckInController {

    List<String> hourLst = Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    List<String> minLst = Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
            "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
            "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
    List<String> monthLst = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
    List<String> yearLst = Arrays.asList("23", "24", "25", "26", "27", "28");


    private static List<Reservation> reservations;
    private static List<ParkingLot> parkingLots;

    public static List<Reservation> getReservations() {
        return reservations;
    }

    public static void setReservations(List<Reservation> reservations) {
        CheckInController.reservations = reservations;
    }

    public static List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public static void setParkingLots(List<ParkingLot> parkingLots) {
        CheckInController.parkingLots = parkingLots;
    }

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cbreserve"
    private CheckBox cbreserve; // Value injected by FXMLLoader

    @FXML // fx:id="errormsg"
    private Text errormsg; // Value injected by FXMLLoader
    @FXML // fx:id="cbsubscribe"
    private CheckBox cbsubscribe; // Value injected by FXMLLoader

    @FXML // fx:id="checkbtn"
    private Button checkbtn; // Value injected by FXMLLoader

    @FXML // fx:id="combodephour"
    private ComboBox<String> combodephour; // Value injected by FXMLLoader

    @FXML // fx:id="combodepmin"
    private ComboBox<String> combodepmin; // Value injected by FXMLLoader

    @FXML // fx:id="combomonth"
    private ComboBox<String> combomonth; // Value injected by FXMLLoader

    @FXML // fx:id="comboyear"
    private ComboBox<String> comboyear; // Value injected by FXMLLoader
    @FXML // fx:id="left"
    private VBox left; // Value injected by FXMLLoader

    @FXML // fx:id="right"
    private VBox right; // Value injected by FXMLLoader

    @FXML // fx:id="tfcardid"
    private TextField tfcardid; // Value injected by FXMLLoader

    @FXML // fx:id="tfcardnum"
    private TextField tfcardnum; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnum"
    private TextField tfcarnum; // Value injected by FXMLLoader

    @FXML // fx:id="tfcvv"
    private TextField tfcvv; // Value injected by FXMLLoader

    @FXML // fx:id="tfdriverid"
    private TextField tfdriverid; // Value injected by FXMLLoader

    @FXML // fx:id="tflicenseplt"
    private TextField tflicenseplt; // Value injected by FXMLLoader

    @FXML // fx:id="tfmail"
    private TextField tfmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfsubid"
    private TextField tfsubid; // Value injected by FXMLLoader


    @FXML
    void cbreserveselect(ActionEvent event) {
        if (cbreserve.isSelected()) {
            tflicenseplt.setDisable(false);
            cbsubscribe.setDisable(false);
            tfcarnum.setDisable(true);
            tfmail.setDisable(true);
            comboyear.setDisable(true);
            combomonth.setDisable(true);
            combodephour.setDisable(true);
            combodepmin.setDisable(true);
            tfcardid.setDisable(true);
            tfcardnum.setDisable(true);
            tfcvv.setDisable(true);
            tfdriverid.setDisable(true);
        } else {
            tflicenseplt.setDisable(true);
            cbsubscribe.setDisable(true);
            tfcarnum.setDisable(false);
            tfmail.setDisable(false);
            comboyear.setDisable(false);
            combomonth.setDisable(false);
            combodephour.setDisable(false);
            combodepmin.setDisable(false);
            tfcardid.setDisable(false);
            tfcardnum.setDisable(false);
            tfcvv.setDisable(false);
            tfdriverid.setDisable(false);
            tfsubid.setDisable(true);
            cbsubscribe.setSelected(false);

        }


    }

    @FXML
    void cbsubscribeselect(ActionEvent event) {
        if (cbsubscribe.isSelected()) {
            tfsubid.setDisable(false);
        } else
            tfsubid.setDisable(true);
    }

    @FXML
    void checkinbtn(ActionEvent event) {
        if (cbreserve.isSelected()){
            if(cbsubscribe.isSelected()){
                //im subscriber
                if(!tflicenseplt.getText().isEmpty()){
                    errormsg.setText("One or more of your information doesn't match");
                    for(Reservation reservation:reservations) {
                        if (reservation.getLicensePlate().equals(tflicenseplt.getText()) && reservation.getSubsriptionID().equals(tfsubid.getText())) {
                            //Check-in info are good
                            try {
                                SimpleClient.getClient().sendToServer(new Message("#AddReserveredCarRequest",reservation));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            errormsg.setText("Car had been added successfully");
                            break;
                        }
                    }
                    if(errormsg.getText().startsWith("Car had been added successfully")){
                        Platform.runLater(() -> {
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Platform.runLater(() -> {
                                        try {
                                            App.setRoot("firstscene");
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                }
                            },2000);
                        });
                    }
                    errormsg.setVisible(true);
                    }
                else {
                    errormsg.setText("Please fill all fields");
                    errormsg.setVisible(true);
                }

            }
            else {
                //one-timer
                errormsg.setText("No reservation found");
                for (Reservation reservation : reservations) {
                    if (reservation.getLicensePlate().equals(tflicenseplt.getText())) {
                        //Check-in info are good
                        try {
                            SimpleClient.getClient().sendToServer(new Message("#AddReserveredCarRequest",reservation));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        errormsg.setText("Car had been added successfully");
                        break;
                    }
                    }
                errormsg.setVisible(true);
                if(errormsg.getText().startsWith("Car had been added successfully")){
                    Platform.runLater(() -> {
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    try {
                                        App.setRoot("firstscene");
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                        },2000);
                    });
                }
                }
        }
        else{
            //mezdamen
            if(!tfdriverid.getText().isEmpty() && !tfcarnum.getText().isEmpty() && !tfmail.getText().isEmpty() && !combodephour.getValue().isEmpty()
            && !combodepmin.getValue().isEmpty() &&!tfcardnum.getText().isEmpty() && !tfcardid.getText().isEmpty() && !tfcvv.getText().isEmpty()
                    && !comboyear.getValue().isEmpty() && !combomonth.getValue().isEmpty()){
                //check-in info good
                LocalDateTime nowTime=LocalDateTime.now();
                LocalDateTime depature=LocalDateTime.of(nowTime.getYear(),nowTime.getMonth(),nowTime.getDayOfMonth(),
                Integer.parseInt(combodephour.getValue()),Integer.parseInt(combodepmin.getValue()));

                Reservation reservation=new Reservation(tfdriverid.getText(),tflicenseplt.getText(),1, LocalDateTime.now(),depature,tfmail.getText(),"Occasional parking",tfcardnum.getText(),
                        LocalDate.of(Integer.parseInt(comboyear.getValue()),Integer.parseInt(combomonth.getValue()),1),tfcvv.getText(),tfcardid.getText());
                try{
                    SimpleClient.getClient().sendToServer(new Message("#AddReserveredCarRequest",reservation));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                errormsg.setText("Car had been added successfully");
                errormsg.setVisible(true);
                if(errormsg.getText().startsWith("Car had been added successfully")) {
                    Platform.runLater(() -> {
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    try {
                                        App.setRoot("firstscene");
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                        }, 2000);
                    });
                }

            }
            else{
                errormsg.setText("Please enter valid information");
                errormsg.setVisible(true);
            }
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
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onReservationCantBeDoneEvent(ReservationCantBeDoneEvent event){
        Platform.runLater(() -> {
            errormsg.setText("No vacancy in this parking lot");
            errormsg.setVisible(true);
            Timer timer=new Timer();
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

        });

    }
    public void initialize(){
        EventBus.getDefault().register(this);
        combodephour.getItems().clear();
        combodephour.setItems(FXCollections.observableArrayList(hourLst));
        combodepmin.getItems().clear();
        combodepmin.setItems(FXCollections.observableArrayList(minLst));
        combomonth.getItems().clear();
        combomonth.setItems(FXCollections.observableArrayList(monthLst));
        comboyear.getItems().clear();
        comboyear.setItems(FXCollections.observableArrayList(yearLst));
        errormsg.setVisible(false);
        tflicenseplt.setDisable(true);
        tfsubid.setDisable(true);

    }

}
