/**
 * Sample Skeleton for 'priceschangescene.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class PriceschangesceneController {
    public PriceschangesceneController() {
    }

    public static List<Price> getPriceList() {
        return priceList;
    }

    public static List<Price> priceList;

    public static void setPriceList(List<Price> priceList) {
        PriceschangesceneController.priceList = priceList;
    }


    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader
    @FXML // fx:id="id_t_c"
    private TableColumn<Price,Long> id_t_c; // Value injected by FXMLLoader
    @FXML // fx:id="parkingtype_t_c"
    private TableColumn<Price, String> parkingtype_t_c; // Value injected by FXMLLoader
    @FXML // fx:id="idbox"
    private ComboBox<Long> idbox; // Value injected by FXMLLoader
    @FXML // fx:id="parkingtypebox"
    private ComboBox<String> parkingtypebox; // Value injected by FXMLLoader

    @FXML // fx:id="paymentmethod_t_c"
    private TableColumn<Price, String> paymentmethod_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="paymentmethodbox"
    private ComboBox<String> paymentmethodbox; // Value injected by FXMLLoader

    @FXML // fx:id="price_t_c"
    private TableColumn<Price, String> price_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="pricebtn"
    private TextField pricebtn; // Value injected by FXMLLoader

    @FXML // fx:id="pricestable"
    private TableView<Price> pricestable; // Value injected by FXMLLoader

    @FXML // fx:id="updatebtn"
    private Button updatebtn; // Value injected by FXMLLoader
    ObservableList<Price> list = FXCollections.observableArrayList(priceList);


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
    void chooseid(ActionEvent event) {
        parkingtypebox.setDisable(false);
        paymentmethodbox.setDisable(false);
        pricebtn.setDisable(false);
        updatebtn.setDisable(false);
    }
    @FXML
    void updatepricestable(ActionEvent event) {
            Price updatedPrice = new Price();
            if(pricebtn.getText()!="")updatedPrice.setPrice(pricebtn.getText());
            if(paymentmethodbox.getValue()!="")updatedPrice.setPaymentPlan(paymentmethodbox.getValue());
            if(parkingtypebox.getValue()!="")updatedPrice.setParkingType(parkingtypebox.getValue());
            if (idbox.getValue()!=null)updatedPrice.setId(idbox.getValue());
            try {
                SimpleClient.getClient().sendToServer(new Message("#ChangePriceRequest", updatedPrice));
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
        App.setRoot("primary");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
     void initialize() {
        parkingtype_t_c.setCellValueFactory(new PropertyValueFactory<Price, String>("parkingType"));
        paymentmethod_t_c.setCellValueFactory(new PropertyValueFactory<Price, String>("paymentPlan"));
        price_t_c.setCellValueFactory(new PropertyValueFactory<Price, String>("price"));
        id_t_c.setCellValueFactory(new PropertyValueFactory<Price,Long>("id"));
        pricestable.setItems(list);
        for (Price price : priceList) {
            idbox.getItems().addAll(price.getId());
            parkingtypebox.getItems().addAll(price.getParkingType());
            paymentmethodbox.getItems().addAll(price.getPaymentPlan());
        }
        parkingtypebox.setDisable(true);
        paymentmethodbox.setDisable(true);
        pricebtn.setDisable(true);
        updatebtn.setDisable(true);
    }
}
