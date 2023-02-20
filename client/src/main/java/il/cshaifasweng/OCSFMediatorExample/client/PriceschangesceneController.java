package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.NewPrice;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

public class PriceschangesceneController {
    public static List<Price> priceList;
    private Price choosenPrice;

    public static List<Price> getPriceList() {
        return priceList;
    }


    public static void setPriceList(List<Price> priceList) {
        PriceschangesceneController.priceList = priceList;
    }

    @FXML // fx:id="backbtn"
    private Button backbtn; // Value injected by FXMLLoader

    @FXML // fx:id="errorMsg"
    private Text errorMsg; // Value injected by FXMLLoader

    @FXML // fx:id="id_t_c"
    private TableColumn<Price, Long> id_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="idbox"
    private TextField idbox; // Value injected by FXMLLoader

    @FXML // fx:id="parkingtype_t_c"
    private TableColumn<Price, String> parkingtype_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="parkingtypebox"
    private TextField parkingtypebox; // Value injected by FXMLLoader

    @FXML // fx:id="paymentmethod_t_c"
    private TableColumn<Price, String> paymentmethod_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="paymentmethodbox"
    private TextField paymentmethodbox; // Value injected by FXMLLoader

    @FXML // fx:id="price_t_c"
    private TableColumn<Price, String> price_t_c; // Value injected by FXMLLoader

    @FXML // fx:id="pricebox"
    private TextField pricebox; // Value injected by FXMLLoader

    @FXML // fx:id="pricestable"
    private TableView<Price> pricestable; // Value injected by FXMLLoader

    @FXML // fx:id="updatebtn"
    private Button updatebtn; // Value injected by FXMLLoader

    @FXML
    void choosePrice(MouseEvent event) {
        errorMsg.setVisible(false);
        choosenPrice=pricestable.getItems().get(pricestable.getSelectionModel().getSelectedIndex());
        if(choosenPrice!=null){
            idbox.setText(""+choosenPrice.getId());
            parkingtypebox.setText(choosenPrice.getParkingType());
            paymentmethodbox.setText(choosenPrice.getPaymentPlan());
            updatebtn.setDisable(false);
        }
    }

    @FXML
    void gotoprimary(ActionEvent event) {
        try {
            App.setRoot("parkinglotworkerpage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void updatepricestable(ActionEvent event) throws IOException {
        if(choosenPrice!=null){
            if(pricebox.getText()!=""){
                NewPrice newPrice=new NewPrice();
                newPrice.setPriceId(Math.toIntExact(choosenPrice.getId()));
                newPrice.setPrice(choosenPrice.getPrice());
                newPrice.setPaymentPlan(choosenPrice.getPaymentPlan());
                newPrice.setParkingType(choosenPrice.getParkingType());
                newPrice.setNewPrice(pricebox.getText());
                try{
                SimpleClient.getClient().sendToServer(new Message("#ChangePriceRequest", newPrice));
                }catch (Exception e){
                    e.printStackTrace();
                }
                resetFields();

            }
            else{
                errorMsg.setText("You didn'n entered a new price!");
                errorMsg.setVisible(true);
            }
        }else{
            errorMsg.setText("You didn'n choose a price to change!");
            errorMsg.setVisible(true);
        }

    }

    private void resetFields() {
        idbox.setText("");
        paymentmethodbox.setText("");
        parkingtypebox.setText("");
        pricebox.setText("");
        choosenPrice=null;
    }

    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshPricesListEvent(RefreshPricesListEvent event) {
            pricestable.getItems().clear();
            setPriceList((List<Price>) event.getPriceList());
            pricestable.setItems(FXCollections.observableArrayList((List<Price>) event.getPriceList()));
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        parkingtype_t_c.setCellValueFactory(new PropertyValueFactory<Price, String>("parkingType"));
        paymentmethod_t_c.setCellValueFactory(new PropertyValueFactory<Price, String>("paymentPlan"));
        price_t_c.setCellValueFactory(new PropertyValueFactory<Price, String>("price"));
        id_t_c.setCellValueFactory(new PropertyValueFactory<Price, Long>("id"));
        pricestable.setItems(FXCollections.observableArrayList(priceList));
        errorMsg.setVisible(false);
        updatebtn.setDisable(true);
    }
}
