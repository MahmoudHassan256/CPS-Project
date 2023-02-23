/**
 * Sample Skeleton for 'checkout.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CheckOutController {
    private static List<Vehicle> vehicleList;
    private static List<SubsriptionClient> subsriptionClientList;
    private static List<Reservation> reservationList;
    private static List<Price> priceList;

    public static List<Price> getPriceList() {
        return priceList;
    }

    public static void setPriceList(List<Price> priceList) {
        CheckOutController.priceList = priceList;
    }

    public static List<SubsriptionClient> getSubsriptionClientList() {
        return subsriptionClientList;
    }

    public static void setSubsriptionClientList(List<SubsriptionClient> subsriptionClientList) {
        CheckOutController.subsriptionClientList = subsriptionClientList;
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }

    public static void setReservationList(List<Reservation> reservationList) {
        CheckOutController.reservationList = reservationList;
    }

    public static List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public static void setVehicleList(List<Vehicle> vehicleList) {
        CheckOutController.vehicleList = vehicleList;
    }

    @FXML // fx:id="CarLocation"
    private AnchorPane CarLocation; // Value injected by FXMLLoader
    @FXML // fx:id="errormsg"
    private Text errormsg; // Value injected by FXMLLoader
    @FXML // fx:id="checkoutBtn"
    private Button checkoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnumber"
    private TextField tfcarnumber; // Value injected by FXMLLoader

    @FXML
    void checkout(ActionEvent event) {
        errormsg.setText("Vehicle isn't in this parking lot");
        for (Vehicle vehicle:vehicleList){
            LocalDateTime time = LocalDateTime.now();
            Integer perhour=0;
            double paymentAmount;
            if(vehicle.getLicensePlate().equals(tfcarnumber.getText())){
                if(vehicle.getType().equals("one-Timer") || vehicle.getType().equals("Occasional parking"))
                {
                    for(Price price: priceList)
                    {
                        if(price.getParkingType().startsWith(vehicle.getType()))
                        {
                            perhour = Integer.parseInt(price.getPrice().replaceAll("[^0-9.]", ""));
                        }
                    }
                    Duration duration = Duration.between(vehicle.getTimeOfArrival(), vehicle.getTimeOfDeparture());
                    Duration lateDuration = Duration.between(vehicle.getTimeOfDeparture(), time);
                    paymentAmount = (int)(duration.toHours()*perhour);
                    if(vehicle.getTimeOfDeparture().isBefore(time))
                    {
                        paymentAmount = (int)(paymentAmount+ lateDuration.toHours()*1.1*perhour);
                    }
                    errormsg.setText("You paid a total of: "+paymentAmount+" Shekel");
                }
                else
                {
                    for(SubsriptionClient subsriptionClient: subsriptionClientList)
                    {
                        if(subsriptionClient.getCarNumberList().contains(vehicle.getLicensePlate()))
                        {
                            Duration duration = Duration.between(vehicle.getTimeOfArrival(), time);
                            subsriptionClient.setRemainingHours((double)(subsriptionClient.getRemainingHours()-duration.toHours()));
                            errormsg.setText("Your remaining hours balance is "+subsriptionClient.getRemainingHours());
                        }
                    }

                }
                try {
                    SimpleClient.getClient().sendToServer(new Message("#RemoveVehicleRequset",vehicle, subsriptionClientList));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Timer timer=new Timer();
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
            }
        }
        errormsg.setVisible(true);


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
    void initialize(){
        errormsg.setVisible(false);
    }
}
