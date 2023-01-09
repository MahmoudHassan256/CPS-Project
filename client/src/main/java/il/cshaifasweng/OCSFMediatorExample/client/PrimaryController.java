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
	void ShowParkings(ActionEvent event) throws IOException{
			SimpleClient.getClient().sendToServer("#ShowParkingLotsRequest");

	}

	@FXML
	void showPrices(ActionEvent event) throws IOException {
		SimpleClient.getClient().sendToServer("#ShowPricesRequest");
	}

	@FXML
	void updatePrices(ActionEvent event) throws IOException {
		SimpleClient.getClient().sendToServer("#UpdatePricesRequest");
	}

}
