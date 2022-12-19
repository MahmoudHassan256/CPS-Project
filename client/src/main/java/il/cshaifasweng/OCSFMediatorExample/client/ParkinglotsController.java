/**
 * Sample Skeleton for 'parkinglots.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ParkinglotsController {
    public ParkinglotsController() {
    }

    public static void setParkingLotList(List<ParkingLot> parkingLotList) {
        ParkinglotsController.parkingLotList = parkingLotList;
    }

    public static List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public static List<ParkingLot> parkingLotList;


    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader

    @FXML // fx:id="capacity_t_c"
    private TableColumn<ParkingLot,Integer> capacity_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="id_t_c"
    private TableColumn<ParkingLot,Integer> id_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="parkinglotstabe"
    private TableView<ParkingLot> parkinglotstabe; // Value injected by FXMLLoader

    ObservableList<ParkingLot> list= FXCollections.observableArrayList(parkingLotList);
    @FXML
    void gotoprimary(ActionEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void initialize(){
        id_t_c.setCellValueFactory(new PropertyValueFactory<ParkingLot,Integer>("id"));
        capacity_t_c.setCellValueFactory(new PropertyValueFactory<ParkingLot,Integer>("capacity"));
        parkinglotstabe.setItems(list);

    }

}
