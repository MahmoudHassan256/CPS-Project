/**
 * Sample Skeleton for 'checkout.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CheckOutController {

    @FXML // fx:id="CarLocation"
    private AnchorPane CarLocation; // Value injected by FXMLLoader

    @FXML // fx:id="checkoutBtn"
    private Button checkoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnumber"
    private TextField tfcarnumber; // Value injected by FXMLLoader

    @FXML
    void checkout(ActionEvent event) {

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
}
