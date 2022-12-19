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
		}


	}
	public void generateParkingLots(){
		Random random=new Random();
		for (int i = 0; i < 10; i++) {
			ParkingLot parkingLot =new ParkingLot(random.nextInt(19));
			session.save(parkingLot);
			session.flush();
		}
	}
	public void generatePrices(){
		Random random=new Random();
		Price price1 =new Price("type1","plan1","price1");session.save(price1);session.flush();
		Price price2 =new Price("type2","plan2","price2");session.save(price2);session.flush();
		Price price3 =new Price("type3","plan3","price3");session.save(price3);session.flush();
		Price price4 =new Price("type4","plan4","price4");session.save(price4);session.flush();
		Price price5 =new Price("type5","plan5","price5");session.save(price5);session.flush();

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
