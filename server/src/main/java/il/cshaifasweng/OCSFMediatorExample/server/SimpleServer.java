package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SimpleServer extends AbstractServer {
	private static SessionFactory sessionFactory=getSessionFactory();
	private static Session session;
	public SimpleServer(int port) {super(port);}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		System.out.println(msgString);
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Pressed the Parking lots btn
		} else if (msgString.startsWith("#ShowParkingLotsRequest")) {
			try {
				session=sessionFactory.openSession();
				ArrayList<ParkingLot> parkingLots=getAllParkingLots();
				client.sendToClient(new Message("#ShowParkingLots",parkingLots));
			}catch (IOException e){
				e.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ShowPricesRequest")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<Price> prices = getAllPrices();
				client.sendToClient(new Message("#ShowPrices", prices));
			}catch (IOException e){
				e.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#UpdatePricesRequest")) {
			try{
				session=sessionFactory.openSession();
				ArrayList<Price> prices = getAllPrices();
				client.sendToClient(new Message("#UpdatePrices",prices));
		}catch (IOException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		} else if (msgString.startsWith("#ChangePriceRequest")) {
			try{
			session=sessionFactory.openSession();
			List<Price> priceList=null;
				Price pricetoupdate=((Price)((Message)msg).getObject());
				try {
					priceList = getAllPrices();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (Price price:priceList){
					if(Objects.equals(price.getId(), pricetoupdate.getId())){
						session.beginTransaction();
						if(pricetoupdate.getPaymentPlan()!=null)price.setPaymentPlan(pricetoupdate.getPaymentPlan());
						if(pricetoupdate.getParkingType()!=null)price.setParkingType(pricetoupdate.getParkingType());
						if(pricetoupdate.getPrice()!=null)price.setPrice(pricetoupdate.getPrice());
						session.save(price);
						session.flush();
						session.getTransaction().commit();
					}
				}
				this.sendToAllClients(new Message("#RefreshPrices",priceList));
			}catch (Exception e){
				e.printStackTrace();
			}
			session.close();
		} else if (msgString.startsWith("#ShowSignInRequest")) {
			try {
				session = sessionFactory.openSession();
				ArrayList<Worker> workers = getAllWorkers();
				client.sendToClient(new Message("#ShowSignIn", workers));
			}catch (IOException e){
				e.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ShowReserveRequest")) {
			try {
				client.sendToClient(new Message("#ShowReserve"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		} else if (msgString.startsWith("#ShowCheckInRequest")) {
			try {
				client.sendToClient(new Message("#ShowCheckIn"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else if (msgString.startsWith("#ShowCheckOutRequest")) {
			try {
				client.sendToClient(new Message("#ShowCheckOut"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else if (msgString.startsWith("#ShowAdminPageRequset")) {
			try {
				updateConnectedWorkerStatus((Worker) ((Message)msg).getObject());
				client.sendToClient(new Message("#ShowAdminPage",((Worker) ((Message)msg).getObject())));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		} else if (msgString.startsWith("#UpdateWorkerState")) {
			updateConnectedWorkerStatus((Worker) ((Message)msg).getObject());
		}else if (msgString.startsWith("#ShowAddDisabledSpacesRequest")){
			try {
				client.sendToClient(new Message("ShowAddDisabledSpaces"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}


	}
	public void updateConnectedWorkerStatus(Worker updateWorker){
		session=sessionFactory.openSession();
		List<Worker> workerList=null;
		try {
			workerList=getAllWorkers();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		for (Worker worker:workerList){
			if(worker.getId()==updateWorker.getId()){
				session.beginTransaction();
				worker.setConnected(!worker.isConnected());
				session.save(worker);
				session.flush();
				session.getTransaction().commit();
				break;
			}
		}
	}
	public void generateParkingLots(){
			ParkingLot parkingLot1 =new ParkingLot(3,3,5);session.save(parkingLot1);session.flush();
			ParkingLot parkingLot2 =new ParkingLot(3,3,8);session.save(parkingLot2);session.flush();
			ParkingLot parkingLot3 =new ParkingLot(3,3,9);session.save(parkingLot3);session.flush();
	}
	public void generatePrices(){
		Random random=new Random();
		Price price1 =new Price("Occasional parking","Per Hour","8₪");session.save(price1);session.flush();
		Price price2 =new Price("Requested One-time parking","Per Hour","7₪");session.save(price2);session.flush();
		Price price3 =new Price("Regular monthly subscription-1 Car","Fixed price","60 hour of Requested One-time parking");session.save(price3);session.flush();
		Price price4 =new Price("Regular monthly subscription-Multi cars","Fixed price","54 hour of Requested One-time parking * Number of cars");session.save(price4);session.flush();
		Price price5 =new Price("Full monthly subscription-1 Car","Fixed price","72 hour of Requested One-time parking");session.save(price5);session.flush();
	}
	public void generateWorkers() throws NoSuchAlgorithmException {
		Worker ParkingLotWorker=new Worker();
		ParkingLotWorker.setEmail("ParkingLotWorker@hotmail.com");ParkingLotWorker.setWorkerId("2");
		ParkingLotWorker.setLastName("Hassan");ParkingLotWorker.setFirstName("Mahmoud");
		ParkingLotWorker.setOccupation("Parking Lot Worker");ParkingLotWorker.setPassword("admin");
		session.save(ParkingLotWorker);session.flush();
		Worker ParkingLotManager=new Worker();
		ParkingLotManager.setEmail("ParkingLotManager@hotmail.com");ParkingLotManager.setWorkerId("2");
		ParkingLotManager.setLastName("Khalil");ParkingLotManager.setFirstName("Mohammed");
		ParkingLotManager.setOccupation("Parking Lot Manager");ParkingLotManager.setPassword("admin");
		session.save(ParkingLotManager);session.flush();
		Worker ChainManager=new Worker();
		ChainManager.setEmail("ChainManager@hotmail.com");ChainManager.setWorkerId("2");
		ChainManager.setLastName("Kais");ChainManager.setFirstName("Ahmad");
		ChainManager.setOccupation("Chain Manager");ChainManager.setPassword("admin");
		session.save(ChainManager);session.flush();
		Worker CustomerService=new Worker();
		CustomerService.setEmail("CustomerService@hotmail.com");CustomerService.setWorkerId("2");
		CustomerService.setLastName("Shalabe");CustomerService.setFirstName("Rojeh");
		CustomerService.setOccupation("Customer Service");CustomerService.setPassword("admin");
		session.save(CustomerService);session.flush();
	}
	private static ArrayList<ParkingLot> getAllParkingLots( )throws Exception{
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<ParkingLot> query=builder.createQuery(ParkingLot.class);
		query.from(ParkingLot.class);
		ArrayList<ParkingLot> data=(ArrayList<ParkingLot>) session.createQuery(query).getResultList();
		return data;
	}
	private static ArrayList<Price> getAllPrices( )throws Exception{
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Price> query=builder.createQuery(Price.class);
		query.from(Price.class);
		ArrayList<Price> data=(ArrayList<Price>) session.createQuery(query).getResultList();
		return data;
	}
	private static ArrayList<Worker> getAllWorkers()throws Exception{
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Worker> query=builder.createQuery(Worker.class);
		query.from(Worker.class);
		ArrayList<Worker> data=(ArrayList<Worker>) session.createQuery(query).getResultList();
		return data;
	}
	private static void addWorkerToParkingLot()throws Exception{
		List<Worker> workers=getAllWorkers();
		List<ParkingLot> parkingLots=getAllParkingLots();

		for (Worker worker:workers){
			Random random=new Random();
			int val=random.nextInt(parkingLots.size());
			ParkingLot tempparkinglot=parkingLots.get(val);
			if(worker.getOccupation().startsWith("Parking Lot Worker")) {
				worker.setParkingLot(tempparkinglot);
				for (Worker worker1 : workers) {
					if (worker1.getOccupation().startsWith("Parking Lot Manager")) {
						worker1.setParkingLot(tempparkinglot);
					for (ParkingLot parkingLot : parkingLots) {
						if (tempparkinglot == parkingLot) {
							parkingLot.addWorker(worker);
							parkingLot.addWorker(worker1);
						}
					}
					}
				}
			}
		}
	}
	public void Connectdata(){
		try {
			SessionFactory sessionFactory=getSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();

			generateParkingLots();
			generatePrices();
			generateWorkers();

			addWorkerToParkingLot();

			session.getTransaction().commit();
		}catch(Exception e){
			if(session!=null){
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	private static SessionFactory getSessionFactory() throws HibernateError {
		Configuration configuration=new Configuration();

		configuration.addAnnotatedClass(ParkingLot.class);
		configuration.addAnnotatedClass(Price.class);
		configuration.addAnnotatedClass(Worker.class);
		configuration.addAnnotatedClass(Vehicle.class);

		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
