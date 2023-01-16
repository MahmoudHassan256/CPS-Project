/**
 * Sample Skeleton for 'complaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ComplaintController {

    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendBtn"
    private Button sendBtn; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnumber"
    private TextField tfcarnumber; // Value injected by FXMLLoader

    @FXML // fx:id="tfcomplaint"
    private TextArea tfcomplaint; // Value injected by FXMLLoader
    @FXML // fx:id="vbitems"
    private VBox vbitems; // Value injected by FXMLLoader
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
    void sendcomplaint(ActionEvent event) throws InterruptedException {
        Complaint  complaint=new Complaint(tfcarnumber.getText(),tfcomplaint.getText(), LocalDateTime.now());
        try {
            SimpleClient.getClient().sendToServer(new Message("#AddComplaintRequest",complaint));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vbitems.getChildren().clear();
        Text thankstext=new Text("Thanks for your time\nredirecting...");
        thankstext.setFont(new Font(36));
        thankstext.setTextAlignment(TextAlignment.CENTER);
        vbitems.getChildren().addAll(thankstext);
        vbitems.setAlignment(Pos.CENTER);
        Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        App.setRoot("firstscene");
                        timer.cancel();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        },1000);

    }

}
