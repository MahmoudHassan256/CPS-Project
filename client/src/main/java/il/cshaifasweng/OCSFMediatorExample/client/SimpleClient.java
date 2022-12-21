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
		}
//
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
