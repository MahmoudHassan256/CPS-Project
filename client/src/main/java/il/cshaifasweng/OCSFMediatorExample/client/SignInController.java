package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SignInController {
    private static List<Worker> workerList;

    public SignInController() {
    }

    public static List<Worker> getWorkerList() {
        return workerList;
    }

    public static void setWorkerList(List<Worker> workerList) {
        SignInController.workerList = workerList;
    }

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="emailbox"
    private TextField emailbox; // Value injected by FXMLLoader
    @FXML // fx:id="errormsg"
    private Text errormsg; // Value injected by FXMLLoader
    @FXML // fx:id="passwordbox"
    private TextField passwordbox; // Value injected by FXMLLoader

    @FXML // fx:id="signinBtn"
    private Button signinBtn; // Value injected by FXMLLoader

    @FXML
    void gotoprimary(ActionEvent event) {
        try {
            App.setRoot("firstscene");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private String hashPassword(String Password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash=md.digest(Password.getBytes());
        StringBuilder sb=new StringBuilder();
        for (byte b:hash){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }
    @FXML
    void signin(ActionEvent event) throws NoSuchAlgorithmException {
    if(emailbox.getText()=="" || passwordbox.getText()==""){
        errormsg.setVisible(true);
        errormsg.setText("Fill All Fields!!");
    }
    else {
        errormsg.setVisible(false);
        for (Worker worker:workerList){
         if(worker.getEmail().toLowerCase().equals(emailbox.getText().toLowerCase())){
             if(worker.getPassword().equals(hashPassword(passwordbox.getText()))){
                 if(!worker.isConnected()){
                     errormsg.setText("is signed");
                     try {
                         SimpleClient.getClient().sendToServer(new Message("#ShowAdminPageRequset",worker));
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                 }
                 else {
                     errormsg.setText("Account is Already Connecter!");
                 }
             }
             else {
                 errormsg.setText("Login Failed: Your Email or Password is incorrect!!");

             }
         }
     }
        errormsg.setVisible(true);
    }
    }
    @FXML
    void initialize(){
        errormsg.setVisible(false);
    }

}
