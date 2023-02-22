/**
 * Sample Skeleton for 'checkout.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Vehicle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CheckOutController {
    private static List<Vehicle> vehicleList;

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
            if(vehicle.getLicensePlate().equals(tfcarnumber.getText())){
                //this is the vehicle
                try {
                    SimpleClient.getClient().sendToServer(new Message("#RemoveVehicleRequset",vehicle));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                errormsg.setText("Car had been removed successfully");
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
