/**
 * Sample Skeleton for 'reserve.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReserveController {

    @FXML // fx:id="addcarbox"
    private VBox addcarbox; // Value injected by FXMLLoader

    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader

    @FXML // fx:id="carNumber"
    private TextField carNumber; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TextField email; // Value injected by FXMLLoader

    @FXML // fx:id="firstname"
    private TextField firstname; // Value injected by FXMLLoader

    @FXML // fx:id="lastname"
    private TextField lastname; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private TextField password; // Value injected by FXMLLoader

    @FXML // fx:id="registerBtn"
    private Button registerBtn; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptiontype"
    private ComboBox<?> subscriptiontype; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptiontypebox"
    private VBox subscriptiontypebox; // Value injected by FXMLLoader

    @FXML // fx:id="type"
    private ComboBox<String> type; // Value injected by FXMLLoader

    @FXML
    void addUser(ActionEvent event) {
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

    @FXML
    void choosetype(ActionEvent event) {
        if(type.getValue()=="Subscription"){
            subscriptiontypebox.setVisible(true);
            addcarbox.setVisible(false);
        } else if (type.getValue()=="One-Timer") {
            addcarbox.setVisible(true);
            subscriptiontypebox.setVisible(false);
        }
        else{
            addcarbox.setVisible(false);
            subscriptiontypebox.setVisible(false);
        }
    }
    @FXML
    void initialize(){
       addcarbox.setVisible(false);
       subscriptiontypebox.setVisible(false);
       type.getItems().addAll("Subscription","One-Timer");
    }
}
