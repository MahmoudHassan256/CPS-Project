package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.NewPrice;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

public class ChainManagerPageController {

    @FXML // fx:id="errorMsg"
    private Text errorMsg; // Value injected by FXMLLoader
    private static Worker worker;
    private NewPrice newPrice;
    private static List<NewPrice> newPriceList;

    public static List<NewPrice> getNewPriceList() {
        return newPriceList;
    }

    public static void setNewPriceList(List<NewPrice> newPriceList) {
        ChainManagerPageController.newPriceList = newPriceList;
    }

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        ChainManagerPageController.worker = worker;
    }

    @FXML // fx:id="confirmpriceBtn"
    private Button confirmpriceBtn; // Value injected by FXMLLoader

    @FXML // fx:id="declinepriceBtn"
    private Button declinepriceBtn; // Value injected by FXMLLoader

    @FXML // fx:id="hello"
    private Text hello; // Value injected by FXMLLoader

    @FXML // fx:id="id_t_c"
    private TableColumn<NewPrice, Integer> id_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="newprice_t_c"
    private TableColumn<NewPrice, String> newprice_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="parkingtype_t_c"
    private TableColumn<NewPrice, String> parkingtype_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="paymentmethod_t_c"
    private TableColumn<NewPrice, String> paymentmethod_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="price_t_c"
    private TableColumn<NewPrice, String> price_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="pricestable"
    private TableView<NewPrice> pricestable; // Value injected by FXMLLoader

    @FXML
    void choosePrice(MouseEvent event) {
        errorMsg.setVisible(false);
        if(!pricestable.getItems().isEmpty()) {
            newPrice = pricestable.getItems().get(pricestable.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void gotoconfirmpricepage(ActionEvent event) throws IOException {
        if(newPrice!=null){
            SimpleClient.getClient().sendToServer(new Message("#UpdateNewPrice",newPrice));

        }else{
            errorMsg.setText("You didn't choose any price");
            errorMsg.setVisible(true);
        }
    }

    @FXML
    void gotodeclinepricepage(ActionEvent event) throws IOException {
        if(newPrice!=null){
            SimpleClient.getClient().sendToServer(new Message("#RemoveNewPrice",newPrice));
        }else{
            errorMsg.setText("You didn't choose any price");
            errorMsg.setVisible(true);
        }
    }

    @FXML
    void gotoshowparkinglotstate(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#ShowParkingLotStateRequest", worker));
    }

    @FXML
    void gotoshowreports(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#ShowReportsRequest", worker));

    }

    @FXML
    void signout(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#UpdateWorkerState", worker));
        App.setRoot("firstscene");
    }

    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshPricesListEvent(RefreshPricesListEvent event) {
        Platform.runLater(() -> {
            pricestable.getItems().clear();
            setNewPriceList(event.getNewPriceList());
            pricestable.setItems(FXCollections.observableArrayList(event.getNewPriceList()));
            pricestable.refresh();
        });
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        hello.setText("Hello " + worker.getName() + "=>" + worker.getOccupation());
        price_t_c.setCellValueFactory(new PropertyValueFactory<>("price"));
        newprice_t_c.setCellValueFactory(new PropertyValueFactory<>("newPrice"));
        id_t_c.setCellValueFactory(new PropertyValueFactory<>("priceId"));
        paymentmethod_t_c.setCellValueFactory(new PropertyValueFactory<>("paymentPlan"));
        parkingtype_t_c.setCellValueFactory(new PropertyValueFactory<>("parkingType"));
        pricestable.setItems(FXCollections.observableArrayList(newPriceList));
        errorMsg.setVisible(false);
    }
}
