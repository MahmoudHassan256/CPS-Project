package il.cshaifasweng.OCSFMediatorExample.client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Spot;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

public class ParkingLotStateController {
    public static List<ParkingLot> parkingLotList;
    public static Worker worker;

    public static List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public static void setParkingLotList(List<ParkingLot> parkingLotList) {
        ParkingLotStateController.parkingLotList = parkingLotList;
    }

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        ParkingLotStateController.worker = worker;
    }

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cbparkinglotId"
    private ComboBox<Integer> cbparkinglotId; // Value injected by FXMLLoader

    @FXML // fx:id="depthNumber"
    private Text depthNumber; // Value injected by FXMLLoader

    @FXML // fx:id="gridheader"
    private Text gridheader; // Value injected by FXMLLoader

    @FXML // fx:id="convertBtn"
    private Button convertBtn; // Value injected by FXMLLoader

    @FXML // fx:id="mainheader"
    private Text mainheader; // Value injected by FXMLLoader
    @FXML // fx:id="nextSet"
    private Button nextSet; // Value injected by FXMLLoader

    @FXML // fx:id="parkinglotcarsGrid"
    private GridPane parkinglotcarsGrid; // Value injected by FXMLLoader

    @FXML // fx:id="parkinglotstateinfo"
    private SplitPane parkinglotstateinfo; // Value injected by FXMLLoader

    @FXML // fx:id="prevSet"
    private Button prevSet; // Value injected by FXMLLoader

    @FXML // fx:id="header"
    private Text header; // Value injected by FXMLLoader
    private Spot choosenCar;
    @FXML
    void goBack(ActionEvent event) {
        if(worker.getOccupation().startsWith("Chain")) {
            try {
                App.setRoot("chainmanagerpage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try{
                App.setRoot("parkinglotworkerpage");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void gotonextset(ActionEvent event) {
        if(depthNumber.getText().equals("1")){
            depthNumber.setText("2");
            nextSet.setVisible(false);
        }
        else {
            depthNumber.setText("1");
        }
        prevSet.setVisible(true);
        parkinglotcarsGrid.getChildren().clear();
        initializeGrid();
    }

    @FXML
    void gotoprevset(ActionEvent event) {
        if(depthNumber.getText().equals("1")){
            depthNumber.setText("0");
            prevSet.setVisible(false);
        }
        else {
            depthNumber.setText("1");
        }
        nextSet.setVisible(true);
        parkinglotcarsGrid.getChildren().clear();
        initializeGrid();
    }
    @FXML
    void chooseParkingLotid(ActionEvent event) {
        if(cbparkinglotId.getValue()==null){
            parkinglotstateinfo.setVisible(false);

        }else{
            gridheader.setText("State for Parking Lot "+cbparkinglotId.getValue());
            parkinglotstateinfo.setVisible(true);
            depthNumber.setText("0");
            prevSet.setVisible(false);
            nextSet.setVisible(true);
            parkinglotcarsGrid.getChildren().clear();
            initializeGrid();
        }
    }
    void initializeGrid(){
        ParkingLot choosenParkingLot =null;
        List<Spot> parkinglotStatue =null;
        if(worker.getOccupation().startsWith("Chain")) {
            choosenParkingLot = parkingLotList.get(cbparkinglotId.getValue() - 1);
            parkinglotStatue = choosenParkingLot.getParkingLotStatus();
        }
        else {
            choosenParkingLot=parkingLotList.get(worker.getParkingLot().getId()-1);
            parkinglotStatue=choosenParkingLot.getParkingLotStatus();

        }
        for(Spot spot:parkinglotStatue) {
            if (spot.getDepthNum()==Integer.parseInt(depthNumber.getText())) {
                FontAwesomeIconView car = new FontAwesomeIconView();
                car.setGlyphName("CAR");
                car.setSize("36");
                if (spot.getCar().equals("open")) {
                    car.setFill(Paint.valueOf("silver"));
                } else if (spot.getCar().equals("damaged")) {
                    car.setFill(Paint.valueOf("red"));
                } else {
                    car.setFill(Paint.valueOf("black"));
                }
                parkinglotcarsGrid.add(car,spot.getRowNum(),spot.getFloorNum());
            }
        }
    }
    @FXML
    void initialize(){
        EventBus.getDefault().register(this);
        for (ParkingLot parkingLot:parkingLotList){
            cbparkinglotId.getItems().addAll(parkingLot.getId());
        }
        header.setVisible(true);
        convertBtn.setDisable(true);
        convertBtn.setVisible(false);
        cbparkinglotId.setVisible(true);
        parkinglotstateinfo.setVisible(false);
        prevSet.setVisible(false);
        depthNumber.setText("0");
        if(worker.getOccupation().startsWith("Parking")){
            cbparkinglotId.setVisible(false);
            gridheader.setText("State for Parking Lot "+worker.getParkingLot().getId());
            header.setVisible(false);
            convertBtn.setVisible(true);
            parkinglotstateinfo.setVisible(true);
            initializeGrid();
        }
    }
    @FXML
    void selectParkingLot(MouseEvent event) {
        try {
            Node clickedNode = event.getPickResult().getIntersectedNode();
            int colIndex = GridPane.getColumnIndex(clickedNode);
            int rowIndex = GridPane.getRowIndex(clickedNode);
            if(((FontAwesomeIconView)clickedNode).getFill().equals(Paint.valueOf("black"))){
                choosenCar=new Spot();
                choosenCar.setCar("damaged");
                choosenCar.setDepthNum(Integer.parseInt(depthNumber.getText()));
                choosenCar.setRowNum(colIndex);
                choosenCar.setFloorNum(rowIndex);
                convertBtn.setDisable(false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshParkingLotsEvent(RefreshParkingLotsEvent event){
        Platform.runLater(() -> {
            parkinglotcarsGrid.getChildren().clear();
            setParkingLotList((List<ParkingLot>) event.getParkingLotList());
            initializeGrid();
        });
    }

    @FXML
    void convertSelected(ActionEvent event) {
       try{
           ParkingLot parkingLot=worker.getParkingLot();
           SimpleClient.getClient().sendToServer(new Message("#updateParkingLotRequest",parkingLot,choosenCar));
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
