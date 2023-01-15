package il.cshaifasweng.OCSFMediatorExample.client;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.VBox;

        import java.io.IOException;

public class FirstSceneController {

    @FXML
    private Button SignInBtn;

    @FXML
    private ImageView backgroundimg;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button checkinBtn;

    @FXML
    private Button checkoutBtn;

    @FXML
    private Button complaintBtn;

    @FXML
    private AnchorPane imageanchor;

    @FXML
    private Button myprofileBtn;

    @FXML
    private Button reserveBtn;

    @FXML
    private Button showparkingBtn;

    @FXML
    private Button subscribeBtn;

    @FXML
    private VBox vboxbuttons;
    @FXML
    void gotocheckout(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowCheckOutRequest");
    }

    @FXML
    void gotocheckin(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowCheckInRequest");

    }

    @FXML
    void gotoreserve(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowReserveRequest");

    }

    @FXML
    void gotoshowparking(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowParkingLotsRequest");

    }

    @FXML
    void gotosignin(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowSignInRequest");
    }

    @FXML
    void gotosubscribe(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowSubscribeRequest");
    }
    @FXML
    void gotocancelreservation(ActionEvent event) {

    }
    @FXML
    void gotocomplaint(ActionEvent event) {

    }

    @FXML
    void gotomyprofile(ActionEvent event) {

    }
}