package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

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
		} else if (strmsg.startsWith("#ShowParkingLots")) {
			EventBus.getDefault().post(new ParkingLotsReceivedEvent((Message) msg));
		} else if (strmsg.startsWith("#ShowPrices")) {
			EventBus.getDefault().post(new PricesReceivedEvent((Message) msg));
		} else if (strmsg.startsWith("#UpdatePrices")) {
			EventBus.getDefault().post(new UpdatePricesReceivedEvent ((Message) msg));
		} else if (strmsg.startsWith("#ShowUpdatedPrices")) {
			EventBus.getDefault().post(new ShowUpdatedPricesEvent((Message)msg));
		} else if (strmsg.startsWith("#ShowReserve")) {
			EventBus.getDefault().post(new ShowReserveEvent((Message) msg));
		} else if (strmsg.startsWith("#ShowCheckIn")) {
			EventBus.getDefault().post(new ShowCheckInEvent((Message) msg));
		} else if (strmsg.startsWith("#ShowCheckOut")) {
			EventBus.getDefault().post(new ShowCheckOutEvent((Message) msg));
		} else if (strmsg.startsWith("#ShowSignIn")) {
			EventBus.getDefault().post(new ShowSignInEvent((Message)msg));
		} else if (strmsg.startsWith("#ShowAdminPage")) {
			EventBus.getDefault().post(new ShowAdminPageEvent((Message) msg));
		} else if (strmsg.startsWith("#RefreshPrices")) {
			EventBus.getDefault().post(new RefreshPricesEvent((Message) msg));
		} else if(strmsg.startsWith("#ChangePriceChainRequest")){
			EventBus.getDefault().post(new ChangePriceChainEvent((Message)msg));
		} else if (strmsg.startsWith("#RefreshComplaintList")) {
			EventBus.getDefault().post(new RefreshComplaintListEvent((Message)msg));
		} else if(strmsg.startsWith("#ShowSubscribe")){
			EventBus.getDefault().post(new ShowSubscribeEvent((Message) msg));
		} else if (strmsg.startsWith("#ShowComplaint")) {
			EventBus.getDefault().post(new ShowComplaintEvent((Message)msg));
		}else if(strmsg.startsWith("#ShowAdminReserveParking")){
			EventBus.getDefault().post(new ShowAdminReserveParkingEvent((Message)msg));
		}
		else if(strmsg.startsWith("#ShowSubscriptionID"))
		{
			EventBus.getDefault().post(new ShowSubscriptionIDEvent((Message)msg));
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
