/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PrimaryController {

	@FXML // fx:id="showparkingbtn"
	private Button showparkingbtn; // Value injected by FXMLLoader

	@FXML // fx:id="showpricesbtn"
	private Button showpricesbtn; // Value injected by FXMLLoader

	@FXML // fx:id="updatepricesbtn"
	private Button updatepricesbtn; // Value injected by FXMLLoader

	@FXML
	void ShowParkings(ActionEvent event) {
		try {
			App.setRoot("parkinglots");
			System.out.print("here");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	void showPrices(ActionEvent event) {
		try {
			App.setRoot("prices");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	void updatePrices(ActionEvent event) {

	}

}
