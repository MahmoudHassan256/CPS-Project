package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
    public void onUpdatePricesReceivedEvent(UpdatePricesReceivedEvent event) {
        PriceschangesceneController.setPriceList((List<Price>) event.getPricestable());
        try {
            App.setRoot("priceschangescene");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowCheckInEvent(ShowCheckInEvent event){
        CheckInController.setReservations((List<Reservation>) event.getReservationList());
        CheckInController.setParkingLots((List<ParkingLot>) event.getParkinglotList());
        try {
            App.setRoot("checkin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowCheckOutEvent(ShowCheckOutEvent event){
        try {
            App.setRoot("checkout");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowReserveEvent(ShowReserveEvent event){
        ReserveController.setParkingLots((List<ParkingLot>)event.getParkingLotsList());
        ReserveController.setSubsriptionClients((List<SubsriptionClient>)event.getSubscriptionList());
        if(event.getWorker()!=null){
            ReserveController.setWorker((Worker) event.getWorker());
        }
        try {
            App.setRoot("reserve");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowSignInEvent(ShowSignInEvent event){
        SignInController.setWorkerList((List<Worker>) event.getWorkersTable());
        try {
            App.setRoot("signin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowCancelReservationEvent(ShowCancelReservationRequest event){
        CancelReservationController.setReservation((List<Reservation>)event.getReservationList());
        CancelReservationController.setSubsriptionClients((List<SubsriptionClient>)event.getSubscriptionList());
        CancelReservationController.setPrices((List<Price>)event.getPrices());
        try {
            App.setRoot("cancelreservation");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowAdminPageEvent(ShowAdminPageEvent event){
        List<Complaint> complaintList=(List<Complaint>) event.getComplaintList();
        List<Refund> refundList=(List<Refund>) event.getRefundList();
        Worker worker=(Worker) event.getWorker();
        if(worker.getOccupation().startsWith("Parking Lot")) {
        ParkingLotWorkerPageController.setWorker((Worker) event.getWorker());
        try {
            App.setRoot("parkinglotworkerpage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        } else if (worker.getOccupation().startsWith("Chain")) {
            ChainManagerPageController.setWorker(worker);
            try {
                App.setRoot("chainmanagerpage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (worker.getOccupation().startsWith("Customer Service")) {
            CustomerServicePageController.setWorker(worker);
            CustomerServicePageController.setComplaintList(complaintList);
            CustomerServicePageController.setRefundList(refundList);
            try {
                App.setRoot("customerservicepage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowSubscribeEvent(ShowSubscribeEvent event){
        SubscribeController.setParkingLots((List<ParkingLot>)event.getParkingLotsList());
        try{
            App.setRoot("subscribe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowComplaintEvent(ShowComplaintEvent event){
        try {
            App.setRoot("complaint");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowReportsEvent(ShowReportsEvent event){
        List<Reservation> reservationList= (List<Reservation>) event.getReservationList();
        List<Complaint> complaintList= (List<Complaint>) event.getComplaintList();
        Worker worker= (Worker) event.getWorker();
        ReportsController.setReservationList(reservationList);
        ReportsController.setWorker(worker);
        ReportsController.setComplaintList(complaintList);
        try {
            App.setRoot("reports");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    @Subscribe
    public void onShowMyProfileEvent(ShowMyProfileEvent event){
        List<Reservation> reservationList= (List<Reservation>) event.getReservationList();
        List<SubsriptionClient> subsriptionClients= (List<SubsriptionClient>) event.getSubscriptionList();
        ProfileController.setSubsriptionClientList(subsriptionClients);
        ProfileController.setReservationList(reservationList);
        try {
            App.setRoot("profile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public static void main(String[] args) {
        launch();
    }

}