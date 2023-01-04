/**
 * Sample Skeleton for 'firstscene.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FirstSceneController {

    @FXML // fx:id="backgroundimg"
    private ImageView backgroundimg; // Value injected by FXMLLoader

    @FXML // fx:id="checkinBtn"
    private Button checkinBtn; // Value injected by FXMLLoader

    @FXML // fx:id="imageanchor"
    private AnchorPane imageanchor; // Value injected by FXMLLoader

    @FXML // fx:id="registerBtn"
    private Button registerBtn; // Value injected by FXMLLoader

    @FXML // fx:id="showparkingBtn"
    private Button showparkingBtn; // Value injected by FXMLLoader

    @FXML // fx:id="vboxbuttons"
    private VBox vboxbuttons; // Value injected by FXMLLoader

    @FXML
    void gotocheckin(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowCheckInRequest");
    }

    @FXML
    void gotoregister(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowRegisterRequest");

    }

    @FXML
    void gotoshowparking(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowParkingLotsRequest");

    }

}
