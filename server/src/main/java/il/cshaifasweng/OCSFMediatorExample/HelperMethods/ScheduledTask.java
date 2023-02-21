package il.cshaifasweng.OCSFMediatorExample.HelperMethods;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.App;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimerTask;


public class ScheduledTask extends TimerTask {
    private static SessionFactory sessionFactory=getSessionFactory();
    private static Session session;

    public void run(){
        session=sessionFactory.openSession();
        ArrayList<Reservation> reservationArrayList=getAll(Reservation.class);
        ArrayList<SubsriptionClient> subsriptionClients=getAll(SubsriptionClient.class);
        LocalDateTime now=LocalDateTime.now();
        for (Reservation reservation:reservationArrayList){
            String email=reservation.getEmail();
            try{
                if(now.plusMinutes(10).isAfter(reservation.getTimeOfArrival()) && !reservation.isNotified() &&
                        !reservation.getTypeOfClient().startsWith("Occasional parking")){
                    //reminder for the reservation before 10 minutes
                    App.sendEmailMethod.SendMailTo(email,"Reminder","Your reservation is is to-do the next 10 minutes!\n" +
                            "please head to your parking lot:"+reservation.getParkingLotID());
                    reservation.setNotified(true);
                    session.beginTransaction();
                    session.save(reservation);
                    session.flush();
                    session.getTransaction().commit();
                }
                if(now.isAfter(reservation.getTimeOfArrival()) && !reservation.isExpired() &&
                        !reservation.getTypeOfClient().startsWith("Occasional parking")){
                    // still didn't come to the check in
                    App.sendEmailMethod.SendMailTo(email,"Late!!!","Your spot that you reserved in Parking lot:"+reservation.getParkingLotID()+"\n"
                    +"Still waiting for you but since you late a fine is added to your payment.");
                    reservation.setExpired(true);
                    session.beginTransaction();
                    session.save(reservation);
                    session.flush();
                    session.getTransaction().commit();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for (SubsriptionClient subsriptionClient:subsriptionClients){
            if(now.plusWeeks(1).isAfter(subsriptionClient.getExpDate()) && !subsriptionClient.isNotified()){
                String email=subsriptionClient.getEmail();
                try {
                    App.sendEmailMethod.SendMailTo(email,"Subscription expiring soon after 7 days","Hello we are sending you this message as" +
                            " a reminder to renew the subscription before it expires");
                    subsriptionClient.setNotified(true);
                    session.beginTransaction();
                    session.save(subsriptionClient);
                    session.flush();
                    session.getTransaction().commit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        session.close();
    }

    public static<T> ArrayList<T> getAll(Class<T> object) {
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<T> query =builder.createQuery(object);
        query.from(object);
        ArrayList<T> data= (ArrayList<T>) session.createQuery(query).getResultList();
        return data;
    }

    private static SessionFactory getSessionFactory() throws HibernateException{
        Configuration configuration=new Configuration();
        configuration.addAnnotatedClass(ParkingLot.class);
        configuration.addAnnotatedClass(Price.class);
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Vehicle.class);
        //configuration.addAnnotatedClass(Spot.class);
        configuration.addAnnotatedClass(Complaint.class);
        configuration.addAnnotatedClass(Refund.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(SubsriptionClient.class);
        configuration.addAnnotatedClass(NewPrice.class);
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
