package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLot;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SimpleServer extends AbstractServer {
	static ArrayList<ParkingLot> parkingLotslist=new ArrayList<>();
	private static SessionFactory sessionFactory=getSessionFactory();
	private static Session session;
	public SimpleServer(int port) {super(port);}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
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
						break;
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			session.close();
		}else if(msgString.startsWith("#UpdatePricesTableRequest")){
			try {
				session=sessionFactory.openSession();
				ArrayList<Price> prices = getAllPrices();
				client.sendToClient(new Message("#ShowUpdatedPrices", prices));
			}catch (IOException e){
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}
	public void generateParkingLots(){
			ParkingLot parkingLot1 =new ParkingLot(250);session.save(parkingLot1);session.flush();
			ParkingLot parkingLot2 =new ParkingLot(350);session.save(parkingLot2);session.flush();
			ParkingLot parkingLot3 =new ParkingLot(450);session.save(parkingLot3);session.flush();
	}
	public void generatePrices(){
		Random random=new Random();
		Price price1 =new Price("Occasional parking","Per Hour","8₪");session.save(price1);session.flush();
		Price price2 =new Price("Requested One-time parking","Per Hour","7₪");session.save(price2);session.flush();
		Price price3 =new Price("Regular monthly subscription-1 Car","Fixed price","60 hour of Requested One-time parking");session.save(price3);session.flush();
		Price price4 =new Price("Regular monthly subscription-Multi cars","Fixed price","54 hour of Requested One-time parking * Number of cars");session.save(price4);session.flush();
		Price price5 =new Price("Full monthly subscription-1 Car","Fixed price","72 hour of Requested One-time parking");session.save(price5);session.flush();

	}
	private static SessionFactory getSessionFactory() throws HibernateError {
		Configuration configuration=new Configuration();
		configuration.addAnnotatedClass(ParkingLot.class);
		configuration.addAnnotatedClass(Price.class);
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	private static ArrayList<ParkingLot> getAllParkingLots( )throws Exception{
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<ParkingLot> query=builder.createQuery(ParkingLot.class);
		query.from(ParkingLot.class);
		ArrayList<ParkingLot> data=(ArrayList<ParkingLot>) session.createQuery(query).getResultList();
		return data;
	}
	private static void PrintAllParkingLots()throws Exception{
		List<ParkingLot> parkingLots = getAllParkingLots();
		for (ParkingLot parkingLot :parkingLots){
			System.out.println("id: "+parkingLot.getId()+" capacity: "+parkingLot.getCapacity());
		}
	}
	private static ArrayList<Price> getAllPrices( )throws Exception{
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Price> query=builder.createQuery(Price.class);
		query.from(Price.class);
		ArrayList<Price> data=(ArrayList<Price>) session.createQuery(query).getResultList();
		return data;
	}
	private static void PrintAllPrices()throws Exception{
		List<Price> prices = getAllPrices();
		for (Price price :prices){
			System.out.println("id: "+price.getId()+" Parking Type: "+price.getParkingType()+" Payment Plane: "+price.getPaymentPlan()+" Price: "+price.getPrice());
		}
	}
	public void Connectdata(){
		try {
			SessionFactory sessionFactory=getSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();

			generateParkingLots();
			generatePrices();

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
}
