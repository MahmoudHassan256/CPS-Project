/**
 * Sample Skeleton for 'alternative.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

public class AlternativeController {

    private static Worker worker;
    private static List<ParkingLot> parkingLotList;

    public static void setWorker(Worker worker) {
        AlternativeController.worker = worker;
    }
    public static void setParkingLotList(List<ParkingLot> parkingLotList) {
        AlternativeController.parkingLotList = parkingLotList;
    }

    @FXML // fx:id="id_t_c"
    private TableColumn<ParkingLot,Integer> id_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="parkinglotstabe"
    private TableView<ParkingLot> parkinglotstabe; // Value injected by FXMLLoader

    @FXML // fx:id="text"
    private Text text; // Value injected by FXMLLoader

    @FXML // fx:id="vacantspaces_t_c"
    private TableColumn<ParkingLot,Integer> vacantspaces_t_c; // Value injected by FXMLLoader

    @FXML
    void goback(ActionEvent event) {
        try {
            App.setRoot("parkinglotworkerpage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshParkingLotsEvent(RefreshParkingLotsEvent event) {
        Platform.runLater(() -> {
            parkinglotstabe.getItems().clear();
            parkinglotstabe.setItems(FXCollections.observableArrayList((List<ParkingLot>) event.getParkingLotList()));
        });
    }

    @FXML
    void initialize(){
        EventBus.getDefault().register(this);
        id_t_c.setCellValueFactory(new PropertyValueFactory<ParkingLot,Integer>("id"));
        vacantspaces_t_c.setCellValueFactory(cellData->{
            ParkingLot parkingLot=cellData.getValue();
            int vacantSpaces=parkingLot.canBeAdded();
            return new SimpleIntegerProperty(vacantSpaces).asObject();
        });
        parkinglotstabe.setItems(FXCollections.observableArrayList(parkingLotList));
    }

}
