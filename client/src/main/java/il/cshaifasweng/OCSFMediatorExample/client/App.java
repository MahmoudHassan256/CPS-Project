package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;

    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("firstscene"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    	
    }

    @SuppressWarnings("unchecked")
        @Subscribe
    public void onParkingLotsReceivedEvent(ParkingLotsReceivedEvent event){
       ParkinglotsController.setParkingLotList((List<ParkingLot>) event.getParkinglotsTable());
        try{
            App.setRoot("parkinglots");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onPricesReceivedEvent(PricesReceivedEvent event){
        PricesController.setPrice((List<Price>) event.getPricesTable());
        try{
            App.setRoot("prices");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Subscribe
    public void onUpdatePricesReceivedEvent(UpdatePricesReceivedEvent event){
        PriceschangesceneController.setPriceList((List<Price>) event.getPricestable());
        try{
            App.setRoot("priceschangescene");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowUpdatedPricesEvent(ShowUpdatedPricesEvent event){
        PricesController.setPrice((List<Price>) event.getPricesTable());
        PriceschangesceneController.setPriceList((List<Price>) event.getPricesTable());
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowCheckInEvent(ShowCheckInEvent event){
        try {
            App.setRoot("checkin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowReserveEvent(ShowReserveEvent event){
        try {
            App.setRoot("reserve");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	public static void main(String[] args) {
        launch();
    }

}