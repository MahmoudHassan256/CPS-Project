package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.io.IOException;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String strmsg=((Message) msg).getMessage();
		System.out.println(strmsg);
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		else if (strmsg.startsWith("#ShowParkingLots")) {
			ParkinglotsController.setParkingLotList((List<ParkingLot>) ((Message)msg).getObject());
			try{
				App.setRoot("parkinglots");
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		else if (strmsg.startsWith("#ShowPrices")) {
			PricesController.setPrice((List<Price>) ((Message) msg).getObject());
			try{
				App.setRoot("prices");
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		else if (strmsg.startsWith("#UpdatePrices")) {
			PriceschangesceneController.setPriceList((List<Price>) ((Message) msg).getObject());
			try {
				App.setRoot("priceschangescene");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (strmsg.startsWith("#ShowUpdatedPrices")) {
			EventBus.getDefault().post(new ShowUpdatedPricesEvent((Message)msg));
		}
		else if (strmsg.startsWith("#ShowReserve")) {
			Message event= (Message) msg;
			ReserveController.setParkingLots((List<ParkingLot>)event.getObject());
			ReserveController.setSubsriptionClients((List<SubsriptionClient>)event.getObject2());
			if(event.getObject3()!=null){
				ReserveController.setWorker((Worker) event.getObject3());
			}
			try {
				App.setRoot("reserve");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if (strmsg.startsWith("#ShowCheckIn")) {
			Message event= (Message) msg;
			CheckInController.setReservations((List<Reservation>) event.getObject2());
			CheckInController.setParkingLots((List<ParkingLot>) event.getObject());
			try {
				App.setRoot("checkin");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if (strmsg.startsWith("#ShowCheckOut")) {
			List<Vehicle> vehicles= (List<Vehicle>) ((Message) msg).getObject();
			CheckOutController.setVehicleList(vehicles);
			try {
				App.setRoot("checkout");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if (strmsg.startsWith("#ShowSignIn")) {
			Message event= (Message) msg;
			SignInController.setWorkerList((List<Worker>) event.getObject());
			try {
				App.setRoot("signin");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if (strmsg.startsWith("#ShowAdminPage")) {
			Message event= (Message) msg;
			List<Complaint> complaintList=(List<Complaint>) event.getObject2();
			List<NewPrice> newPriceList= (List<NewPrice>) event.getObject4();
			Worker worker=(Worker) event.getObject();
			if(worker.getOccupation().startsWith("Parking Lot")) {
				ParkingLotWorkerPageController.setWorker((Worker) event.getObject());
				try {
					App.setRoot("parkinglotworkerpage");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} else if (worker.getOccupation().startsWith("Chain")) {
				ChainManagerPageController.setWorker(worker);
				ChainManagerPageController.setNewPriceList(newPriceList);
				try {
					App.setRoot("chainmanagerpage");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} else if (worker.getOccupation().startsWith("Customer Service")) {
				CustomerServicePageController.setWorker(worker);
				CustomerServicePageController.setComplaintList(complaintList);
				try {
					App.setRoot("customerservicepage");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		else if (strmsg.startsWith("#RefreshPricesList")) {
			EventBus.getDefault().post(new RefreshPricesListEvent((Message)msg));
		}
		else if (strmsg.startsWith("#RefreshComplaintList")) {
			EventBus.getDefault().post(new RefreshComplaintListEvent((Message)msg));
		}
		else if(strmsg.startsWith("#ShowSubscribe")){
			Message event=(Message) msg;
			SubscribeController.setParkingLots((List<ParkingLot>)event.getObject());
			try{
				App.setRoot("subscribe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if (strmsg.startsWith("#ShowComplaint")) {
			try {
				App.setRoot("complaint");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if(strmsg.startsWith("#ShowSubscriptionID")) {
			EventBus.getDefault().post(new ShowSubscriptionIDEvent((Message)msg));
		}
		else if (strmsg.startsWith("#ShowReports")) {
			Message event =(Message) msg;
			List<Reservation> reservationList= (List<Reservation>) event.getObject();
			List<Complaint> complaintList= (List<Complaint>) event.getObject2();
			Worker worker= (Worker) event.getObject3();
			List<ParkingLot> parkingLotList= (List<ParkingLot>) event.getObject4();
			ReportsController.setReservationList(reservationList);
			ReportsController.setWorker(worker);
			ReportsController.setComplaintList(complaintList);
			ReportsController.setParkingLotList(parkingLotList);
			try {
				App.setRoot("reports");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		else if(strmsg.startsWith("#ShowCancelReservation")) {
			Message event =(Message) msg;
			CancelReservationController.setReservation((List<Reservation>)event.getObject());
			CancelReservationController.setSubsriptionClients((List<SubsriptionClient>)event.getObject2());
			CancelReservationController.setPrices((List<Price>)event.getObject3());
			try {
				App.setRoot("cancelreservation");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if (strmsg.startsWith("#RefreshReservationList")) {
			EventBus.getDefault().post(new RefreshReservationListEvent((Message)msg));
		}
		else if (strmsg.startsWith("#ShowMyProfile")) {
			Message event =(Message) msg;
			List<Reservation> reservationList= (List<Reservation>) event.getObject();
			List<SubsriptionClient> subsriptionClients= (List<SubsriptionClient>) event.getObject2();
			ProfileController.setSubsriptionClientList(subsriptionClients);
			ProfileController.setReservationList(reservationList);
			try {
				App.setRoot("profile");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else if (strmsg.startsWith("#ReservationCantBeDone")) {
			EventBus.getDefault().post(new ReservationCantBeDoneEvent((Message)msg));
		}
		else if(strmsg.startsWith("#ReservationDone")){
			EventBus.getDefault().post(new ReservationDoneEvent((Message)msg));
		}
		else if (strmsg.startsWith("#ShowParkingLotState")) {
			Message event=(Message) msg;
			Worker worker = (Worker) event.getObject();
			List<ParkingLot> parkingLotList= (List<ParkingLot>) event.getObject2();
			ParkingLotStateController.setParkingLotList(parkingLotList);
			ParkingLotStateController.setWorker(worker);
			try{
				App.setRoot("parkinglotstate");
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		else if (strmsg.startsWith("#RefreshParkingLots")) {
			EventBus.getDefault().post(new RefreshParkingLotsEvent((Message)msg));
		}

	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
