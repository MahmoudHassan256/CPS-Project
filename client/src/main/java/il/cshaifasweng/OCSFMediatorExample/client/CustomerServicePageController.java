package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerServicePageController {

    private static Worker worker;
    private static List<Complaint> complaintList;
    private Complaint choosencomplaint;
    private List<Complaint> openComplaints;
    private List<Complaint> closedComplaints;
    private List<Complaint> priorityComplaints;



    public static List<Complaint> getComplaintList() {
        return complaintList;
    }

    public static void setComplaintList(List<Complaint> complaintList) {
        CustomerServicePageController.complaintList = complaintList;
    }

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        CustomerServicePageController.worker = worker;
    }
    @FXML // fx:id="closedTab"
    private Tab closedTab; // Value injected by FXMLLoader

    @FXML // fx:id="closedTable"
    private TableView<Complaint> closedTable; // Value injected by FXMLLoader

    @FXML // fx:id="complaintDescrition"
    private TextArea complaintDescrition; // Value injected by FXMLLoader


    @FXML // fx:id="descriptionCol1"
    private TableColumn<Complaint,String> descriptionCol1; // Value injected by FXMLLoader
    @FXML // fx:id="descriptionCol2"
    private TableColumn<Complaint,String> descriptionCol2; // Value injected by FXMLLoader
    @FXML // fx:id="descriptionCol3"
    private TableColumn<Complaint,String> descriptionCol3; // Value injected by FXMLLoader
    @FXML // fx:id="deservefundCheckBox"
    private CheckBox deservefundCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="errormsg"
    private Text errormsg; // Value injected by FXMLLoader
    @FXML // fx:id="hello"
    private Text hello; // Value injected by FXMLLoader

    @FXML // fx:id="identifierCol1"
    private TableColumn<Complaint,Integer> identifierCol1; // Value injected by FXMLLoader
    @FXML // fx:id="identifierCol2"
    private TableColumn<Complaint,Integer> identifierCol2; // Value injected by FXMLLoader
     @FXML // fx:id="identifierCol3"
    private TableColumn<Complaint,Integer> identifierCol3; // Value injected by FXMLLoader
    @FXML // fx:id="openTab"
    private Tab openTab; // Value injected by FXMLLoader

    @FXML // fx:id="openTable"
    private TableView<Complaint> openTable; // Value injected by FXMLLoader

    @FXML // fx:id="priorityTab"
    private Tab priorityTab; // Value injected by FXMLLoader

    @FXML // fx:id="priorityTable"
    private TableView<Complaint> priorityTable; // Value injected by FXMLLoader

    @FXML // fx:id="refundValue"
    private TextField refundValue; // Value injected by FXMLLoader

    @FXML // fx:id="responseDescription"
    private TextArea responseDescription; // Value injected by FXMLLoader

    @FXML // fx:id="submitBtn"
    private Button submitBtn; // Value injected by FXMLLoader

    @FXML // fx:id="submitiondateCol1"
    private TableColumn<Complaint,LocalDateTime> submitiondateCol1; // Value injected by FXMLLoader
    @FXML // fx:id="submitiondateCol2"
    private TableColumn<Complaint,LocalDateTime> submitiondateCol2; // Value injected by FXMLLoader
    @FXML // fx:id="submitiondateCol3"
    private TableColumn<Complaint,LocalDateTime> submitiondateCol3; // Value injected by FXMLLoader

    @FXML // fx:id="tabTable"
    private TabPane tabTable; // Value injected by FXMLLoader

    ObservableList<Complaint> complaints= FXCollections.observableArrayList(complaintList);
    @FXML
    void signout(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#UpdateWorkerState",worker));
        App.setRoot("firstscene");
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onRefreshComplaintListEvent(RefreshComplaintListEvent event){
        openTable.getItems().clear();
        closedTable.getItems().clear();
        priorityTable.getItems().clear();
        setComplaintList((List<Complaint>) event.getComplaintList());
        filter((List<Complaint>) event.getComplaintList());
    }
    @FXML
    void SubmitResponse(ActionEvent event) throws InterruptedException {
        if(choosencomplaint==null){
            errormsg.setVisible(true);
            errormsg.setText("Please choose a complaint to response to...");
        }
        else if(responseDescription.getText().equals("")){
            errormsg.setVisible(true);
            errormsg.setText("Please insert a response...");
        }
        else if(deservefundCheckBox.isSelected() && refundValue.getText().equals("")){
            errormsg.setVisible(true);
            errormsg.setText("Please insert refund value...");
        }
        else{
            errormsg.setVisible(false);
            Complaint updatedComplaint = new Complaint(choosencomplaint.getCarNumber(),choosencomplaint.getDescription(),choosencomplaint.getSubmitionDate());
            updatedComplaint.setResponse(responseDescription.getText());
            updatedComplaint.setStatus("closed");
            if(deservefundCheckBox.isSelected()) {
                updatedComplaint.setRefundValue(refundValue.getText());
            }
            try {
                SimpleClient.getClient().sendToServer(new Message("#UpdateComplaint",updatedComplaint));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void setDeserveRefund(ActionEvent event) {
        if(deservefundCheckBox.isSelected()){
            refundValue.setDisable(false);
        }
        else {
            refundValue.setDisable(true);
        }
    }

    @FXML
    void viewclosedinquiry(MouseEvent event) {
        choosencomplaint=closedTable.getItems().get(closedTable.getSelectionModel().getSelectedIndex());
        complaintDescrition.setText(choosencomplaint.getDescription());
        responseDescription.setText(choosencomplaint.getResponse());
        responseDescription.setDisable(true);
        refundValue.setText(choosencomplaint.getRefundValue());
        refundValue.setDisable(true);
        submitBtn.setDisable(true);
        deservefundCheckBox.setDisable(true);
    }

    @FXML
    void viewopeninquiry(MouseEvent event) {
        choosencomplaint=openTable.getItems().get(openTable.getSelectionModel().getSelectedIndex());
        complaintDescrition.setText(choosencomplaint.getDescription());
        responseDescription.setText("");
        responseDescription.setDisable(false);
        submitBtn.setDisable(false);
        deservefundCheckBox.setDisable(false);

    }

    @FXML
    void viewpriorityinquiry(MouseEvent  event) {
        choosencomplaint=priorityTable.getItems().get(priorityTable.getSelectionModel().getSelectedIndex());
        complaintDescrition.setText(choosencomplaint.getDescription());
        responseDescription.setText("");
        responseDescription.setDisable(false);
        submitBtn.setDisable(false);
        deservefundCheckBox.setDisable(false);

    }
    @FXML
    void filter(List<Complaint> complaintsList){
        priorityComplaints=new ArrayList<>();
        openComplaints=new ArrayList<>();
        closedComplaints=new ArrayList<>();
        for (Complaint complaint:complaintsList){
            if(complaint.getStatus().equals("pending")){
              if(Duration.between(complaint.getSubmitionDate(),LocalDateTime.now()).toHours()<24){
                  priorityComplaints.add(complaint);
              }
              openComplaints.add(complaint);
            }
            else if (complaint.getStatus().equals("closed")) {
                closedComplaints.add(complaint);
            }
        }
        if(priorityComplaints!=null)priorityTable.setItems(FXCollections.observableList((ObservableList<Complaint>)FXCollections.observableArrayList(priorityComplaints)));
        if(openComplaints!=null) openTable.setItems(FXCollections.observableList((ObservableList<Complaint>)FXCollections.observableArrayList(openComplaints)));
        if(closedComplaints!=null) closedTable.setItems(FXCollections.observableList((ObservableList<Complaint>)FXCollections.observableArrayList(closedComplaints)));
    }
    @FXML
    void initialize(){
        EventBus.getDefault().register(this);
        hello.setText("Hello "+worker.getName()+"=>"+worker.getOccupation());
        identifierCol1.setCellValueFactory(new PropertyValueFactory<Complaint,Integer>("id"));
        submitiondateCol1.setCellValueFactory(new PropertyValueFactory<Complaint,LocalDateTime>("submitionDate"));
        descriptionCol1.setCellValueFactory(new PropertyValueFactory<Complaint,String>("description"));
        identifierCol2.setCellValueFactory(new PropertyValueFactory<Complaint,Integer>("id"));
        submitiondateCol2.setCellValueFactory(new PropertyValueFactory<Complaint,LocalDateTime>("submitionDate"));
        descriptionCol2.setCellValueFactory(new PropertyValueFactory<Complaint,String>("description"));
        identifierCol3.setCellValueFactory(new PropertyValueFactory<Complaint,Integer>("id"));
        submitiondateCol3.setCellValueFactory(new PropertyValueFactory<Complaint,LocalDateTime>("submitionDate"));
        descriptionCol3.setCellValueFactory(new PropertyValueFactory<Complaint,String>("description"));
        filter(complaintList);
        errormsg.setVisible(false);
        refundValue.setDisable(true);
    }
}


