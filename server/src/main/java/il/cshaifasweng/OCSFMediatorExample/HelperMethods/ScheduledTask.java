package il.cshaifasweng.OCSFMediatorExample.HelperMethods;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
        for (Reservation reservation:reservationArrayList){
            String email=reservation.getEmail();
            String temp="minute";
            try{
                LocalDateTime now=LocalDateTime.now();
                if(now.plusMinutes(10).isAfter(reservation.getTimeOfArrival()) && !reservation.isNotified()){
                    //reminder for the reservation before 10 minutes
                    SendEmailMethod sendEmailMethod=new SendEmailMethod();
                    sendEmailMethod.SendMailTo(email,"Reminder","Your reservation is is to-do the next "+temp+"!\n" +
                            "please head to your parking lot:"+reservation.getParkingLotID());
                    reservation.setNotified(true);
                    session.beginTransaction();
                    session.save(reservation);
                    session.flush();
                    session.getTransaction().commit();
                }
               /* if(now.isAfter(reservation.getTimeOfArrival())){
                    // still didn't come to the check in
                    SendEmailMethod.SendMailTo(email,"Late!!!","Your spot that you reserved in Parking lot:"+reservation.getParkingLotID()+"\n"
                    +"Still waiting for you but since you late a fine is added to your payment.");
                    session.beginTransaction();
                    session.save(reservation);
                    session.flush();
                    session.getTransaction().commit();
                }*/
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
