package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import il.cshaifasweng.OCSFMediatorExample.entities.Reservation;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CancelReservationController {
    private static List<Reservation> reservation;

    public static List<Reservation> getReservation() {
        return reservation;
    }

    public static void setReservation(List<Reservation> reservation) {
        CancelReservationController.reservation = reservation;
    }

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

    @FXML
    void cancelReservationPressed(ActionEvent event) {
        labelErrorInput.setVisible(false);
        labelNoReservation.setVisible(false);
        if (!tfLicensePlate.getText().isEmpty() && StringUtils.isNumeric(tfLicensePlate.getText()))
        {
            Reservation reservation2 = null;
            for (Reservation reservation1 : reservation) {
                if (reservation1.getLicensePlate().contains(tfLicensePlate.getText())) {
                    reservation2 = reservation1;
                }
            }
            if(reservation2==null)
            {
                labelNoReservation.setVisible(true);
            }
            else
            {
                Reservation reservation1=reservation2;
                try {
                    SimpleClient.getClient().sendToServer(new Message("#CancelReservationRequest", reservation1));
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

        }
        else {
            labelErrorInput.setVisible(true);
        }
    }
    @FXML
    void gotoprimary(ActionEvent event) {
        try {
            App.setRoot("firstscene");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
