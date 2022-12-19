/**
 * Sample Skeleton for 'prices.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Price;
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

public class PricesController {
    public PricesController() {
    }

    public static List<Price> getPriceList() {
        return priceList;
    }

    public static List<Price> priceList;
    public static void setPrice(List<Price> priceList){
       PricesController.priceList=priceList;
    }

    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader

    @FXML // fx:id="parkingtype_t_c"
    private TableColumn<Price,String> parkingtype_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="paymentplan_t_c"
    private TableColumn<Price,String> paymentplan_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="price_t_c"
    private TableColumn<Price,String> price_t_c; // Value injected by FXMLLoader
    @FXML // fx:id="pricestable"
    private TableView<Price> pricestable; // Value injected by FXMLLoader

    ObservableList<Price> list= FXCollections.observableArrayList(priceList);

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
        parkingtype_t_c.setCellValueFactory(new PropertyValueFactory<Price,String>("parkingType"));
        paymentplan_t_c.setCellValueFactory(new PropertyValueFactory<Price,String>("paymentPlan"));
        price_t_c.setCellValueFactory(new PropertyValueFactory<Price,String>("price"));
    pricestable.setItems(list);
    }

}
