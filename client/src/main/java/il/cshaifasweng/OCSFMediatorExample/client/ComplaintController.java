/**
 * Sample Skeleton for 'complaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ComplaintController {

    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendBtn"
    private Button sendBtn; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnumber"
    private TextField tfcarnumber; // Value injected by FXMLLoader

    @FXML // fx:id="tfcomplaint"
    private TextArea tfcomplaint; // Value injected by FXMLLoader

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
    void sendcomplaint(ActionEvent event) {

    }

}
