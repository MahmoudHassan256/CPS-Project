/**
 * Sample Skeleton for 'checkin.fxml' Controller Class
 */
/**
 * Sample Skeleton for 'checkin.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.util.Arrays;
import java.util.List;


public class CheckInController {
    List<String> hourLst  = Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    List<String> minLst = Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
            "24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44",
            "45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
    List<String> monthLst = Arrays.asList("01","02,","03","04,","05","06,","07","08,","09","10,","11","12");
    List<String> yearLst = Arrays.asList("23","24","25","26","27","28");

    ObservableList<String> hourLst1 = FXCollections.observableArrayList("00" ,"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cbreserve"
    private CheckBox cbreserve; // Value injected by FXMLLoader

    @FXML // fx:id="cbsubscribe"
    private CheckBox cbsubscribe; // Value injected by FXMLLoader

    @FXML // fx:id="checkbtn"
    private Button checkbtn; // Value injected by FXMLLoader

    @FXML // fx:id="combodephour"
    private ComboBox<String> combodephour; // Value injected by FXMLLoader

    @FXML // fx:id="combodepmin"
    private ComboBox<String> combodepmin; // Value injected by FXMLLoader

    @FXML // fx:id="combomonth"
    private ComboBox<String> combomonth; // Value injected by FXMLLoader

    @FXML // fx:id="comboyear"
    private ComboBox<String> comboyear; // Value injected by FXMLLoader

    @FXML // fx:id="tfcardid"
    private TextField tfcardid; // Value injected by FXMLLoader

    @FXML // fx:id="tfcardnum"
    private TextField tfcardnum; // Value injected by FXMLLoader

    @FXML // fx:id="tfcarnum"
    private TextField tfcarnum; // Value injected by FXMLLoader

    @FXML // fx:id="tfcvv"
    private TextField tfcvv; // Value injected by FXMLLoader

    @FXML // fx:id="tfdriverid"
    private TextField tfdriverid; // Value injected by FXMLLoader

    @FXML // fx:id="tflicenseplt"
    private TextField tflicenseplt; // Value injected by FXMLLoader

    @FXML // fx:id="tfmail"
    private TextField tfmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfsubid"
    private TextField tfsubid; // Value injected by FXMLLoader

    @FXML
    void cbreserveselect(ActionEvent event) {

    }

    @FXML
    void cbsubscribeselect(ActionEvent event) {

    }

    @FXML
    void checkinbtn(ActionEvent event) {

    }

    @FXML
    void gotoprimary(ActionEvent event) {

        try {
            App.setRoot("firstscene");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

        public void initialize(){
        System.out.println("^^^^^^^^^^^^^^^^^^^^^");
        combodephour.getItems().clear();
        combodephour.setItems(FXCollections.observableArrayList(hourLst));
        combodepmin.getItems().clear();
        combodepmin.setItems(FXCollections.observableArrayList(minLst));
        combomonth.getItems().clear();
        combomonth.setItems(FXCollections.observableArrayList(monthLst));
        comboyear.getItems().clear();
        comboyear.setItems(FXCollections.observableArrayList(yearLst));
    }

}
