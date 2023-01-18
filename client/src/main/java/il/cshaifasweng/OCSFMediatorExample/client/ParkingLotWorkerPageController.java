package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class ParkingLotWorkerPageController {
    public ParkingLotWorkerPageController() {
    }

    private static Worker worker;

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        ParkingLotWorkerPageController.worker = worker;
    }


    @FXML // fx:id="hello"
    private Text hello; // Value injected by FXMLLoader
    @FXML // fx:id="sendreportBtn"
    private Button sendreportBtn; // Value injected by FXMLLoader

    @FXML // fx:id="updatepriceBtn"
    private Button updatepriceBtn; // Value injected by FXMLLoader

    @FXML
    void signout(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#UpdateWorkerState",worker));
        App.setRoot("firstscene");
    }
    @FXML
    void gotoadddisablespace(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowAddDisabledSpacesRequest");

    }

    @FXML
    void gotorefertoalternativeparkinglot(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowRefereToAnotherPLRequest");
    }

    @FXML
    void gotoreservespace(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#ShowReserveRequest",worker));

    }
    @FXML
    void gotosendreport(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#ShowSendReportRequest");

    }
    @FXML
    void gotoupdateprices(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#UpdatePricesRequest");
    }
    @FXML
    void initialize(){
        hello.setText("Hello "+worker.getName()+"=>"+worker.getOccupation());
        sendreportBtn.setVisible(false);
        updatepriceBtn.setVisible(false);
        if(worker.getOccupation().startsWith("Parking Lot Manager")){
            sendreportBtn.setVisible(true);
            updatepriceBtn.setVisible(true);
        }

    }

}
