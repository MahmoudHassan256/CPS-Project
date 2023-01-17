package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class ChainManagerPageController {
    private static Price updatedPrice;
    private static Worker worker;

    public static Price getUpdatedPrice() {
        return updatedPrice;
    }

    public static void setUpdatedPrice(Price updatedPrice) {
        ChainManagerPageController.updatedPrice = updatedPrice;
    }

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        ChainManagerPageController.worker = worker;
    }


    @FXML // fx:id="newprice"
    private TextArea newprice; // Value injected by FXMLLoader

    @FXML // fx:id="pricesbox"
    private HBox pricesbox; // Value injected by FXMLLoader

    @FXML // fx:id="pricetoupdate"
    private Text pricetoupdate; // Value injected by FXMLLoader
    @FXML // fx:id="hello"
    private Text hello; // Value injected by FXMLLoader

    @FXML
    void signout(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#UpdateWorkerState",worker));
        App.setRoot("firstscene");
    }
    @FXML // fx:id="confirmpriceBtn"
    private Button confirmpriceBtn; // Value injected by FXMLLoader
    @FXML
    void gotoconfirmpricepage(ActionEvent event) throws IOException {
    SimpleClient.getClient().sendToServer(new Message("#ChangePriceGrantedRequest",updatedPrice));
    newprice.setText("");
    pricetoupdate.setVisible(false);
    pricesbox.setVisible(false);
    confirmpriceBtn.setDisable(true);
    }
    @FXML
    void gotoshowparkinglotstate(ActionEvent event) throws IOException {
    SimpleClient.getClient().sendToServer("#ShowParkingLotStateRequest");
    }

    @FXML
    void gotoshowreports(ActionEvent event) throws IOException {
    SimpleClient.getClient().sendToServer(new Message("#ShowReportsRequest",worker));
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onChangePriceChainEvent(ChangePriceChainEvent event){
        confirmpriceBtn.setDisable(false);
        pricesbox.setVisible(true);
        pricetoupdate.setVisible(true);
        setUpdatedPrice((Price) event.getUpdatedprice());
        newprice.setText(event.getUpdatedprice().toString());
    }

    @FXML
    void initialize(){
        EventBus.getDefault().register(this);
        hello.setText("Hello "+worker.getName()+"=>"+worker.getOccupation());
        if(updatedPrice!=null) {
            confirmpriceBtn.setDisable(true);
            pricesbox.setVisible(false);
            pricetoupdate.setVisible(false);
        }

    }
}
