package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class CustomerServicePageController {

    private static Worker worker;

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        CustomerServicePageController.worker = worker;
    }


    @FXML // fx:id="hello"
    private Text hello; // Value injected by FXMLLoader

    @FXML
    void signout(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#UpdateWorkerState",worker));
        App.setRoot("firstscene");
    }
    @FXML
    void initialize(){
        hello.setText("Hello "+worker.getName()+"=>"+worker.getOccupation());

    }
}


