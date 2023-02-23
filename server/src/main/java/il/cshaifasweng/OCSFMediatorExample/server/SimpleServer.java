package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.HelperMethods.CarAddRemoveMethods;
import il.cshaifasweng.OCSFMediatorExample.HelperMethods.ScheduledTask;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

public class SimpleServer extends AbstractServer {
    private static SessionFactory sessionFactory = getSessionFactory();
    private static Session session;
    private static Timer timer = new Timer();
    private static ScheduledTask scheduledTask = new ScheduledTask();

    public SimpleServer(int port) {
        super(port);
    }

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
                session = sessionFactory.openSession();
                ArrayList<ParkingLot> parkingLots = getAll(ParkingLot.class);
                client.sendToClient(new Message("#ShowParkingLots", parkingLots));
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ShowPricesRequest")) {
            try {
                session = sessionFactory.openSession();
                ArrayList<Price> prices = getAll(Price.class);
                client.sendToClient(new Message("#ShowPrices", prices));
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#UpdatePricesRequest")) {
            try {
                session = sessionFactory.openSession();
                ArrayList<Price> prices = getAll(Price.class);
                client.sendToClient(new Message("#UpdatePrices", prices));
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ChangePriceRequest")) {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(((Message) msg).getObject());
                session.flush();
                session.getTransaction().commit();
                List<NewPrice> newPriceList = getAll(NewPrice.class);
                List<Price> priceList = getAll(Price.class);
                this.sendToAllClients(new Message("#RefreshPricesList", priceList, newPriceList));
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ChangePriceGrantedRequest")) {
            try {
                session = sessionFactory.openSession();
                List<Price> priceList = null;
                Price pricetoupdate = ((Price) ((Message) msg).getObject());
                try {
                    priceList = getAll(Price.class);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                for (Price price : priceList) {
                    if (Objects.equals(price.getId(), pricetoupdate.getId())) {
                        session.beginTransaction();
                        if (pricetoupdate.getPaymentPlan() != null)
                            price.setPaymentPlan(pricetoupdate.getPaymentPlan());
                        if (pricetoupdate.getParkingType() != null)
                            price.setParkingType(pricetoupdate.getParkingType());
                        if (pricetoupdate.getPrice() != null) price.setPrice(pricetoupdate.getPrice());
                        session.save(price);
                        session.flush();
                        session.getTransaction().commit();
                    }
                }
                this.sendToAllClients(new Message("#RefreshPricesList", priceList, null));
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ShowSignInRequest")) {
            try {
                session = sessionFactory.openSession();
                ArrayList<Worker> workers = getAll(Worker.class);
                client.sendToClient(new Message("#ShowSignIn", workers));
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ShowReserveRequest")) {
            try {
                session = sessionFactory.openSession();
                List<ParkingLot> parkingLots = getAll(ParkingLot.class);
                List<SubsriptionClient> subsriptionClients = getAll(SubsriptionClient.class);
                Worker worker = (Worker) ((Message) msg).getObject();
                if (worker != null) {
                    client.sendToClient(new Message("#ShowReserve", parkingLots, subsriptionClients, worker));
                } else {
                    client.sendToClient(new Message("#ShowReserve", parkingLots, subsriptionClients));
                }
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (msgString.startsWith("#AddReservationRequest")) {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                Reservation reservation = (Reservation) ((Message) msg).getObject();
                if (!canBeAdded(reservation)) {
                    this.sendToAllClients(new Message("#ReservationCantBeDone"));
                } else {
                    session.save(reservation);
                    session.flush();
                    session.getTransaction().commit();
                    List<Reservation> reservationList = getAll(Reservation.class);
                    this.sendToAllClients(new Message("#RefreshReservationList", reservationList));
                    client.sendToClient(new Message("#ReservationDone"));
                }
                session.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#ShowCheckInRequest")) {
            try {
                session = sessionFactory.openSession();
                List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
                List<Reservation> reservationList = getAll(Reservation.class);
                client.sendToClient(new Message("#ShowCheckIn", parkingLotList, reservationList));
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#ShowCheckOutRequest")) {
            try {
                session=sessionFactory.openSession();
                session.beginTransaction();
                List<Vehicle> vehicles=getAll(Vehicle.class);
                List<SubsriptionClient> subsriptionClients = getAll((SubsriptionClient.class));
                List<Reservation> reservations = getAll(Reservation.class);
                List<Price> prices = getAll(Price.class);
                client.sendToClient(new Message("#ShowCheckOut",vehicles, subsriptionClients, reservations, prices));
                session.getTransaction().commit();
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#ShowAdminPageRequset")) {
            try {
                session = sessionFactory.openSession();
                List<Worker> workerList = null;
                workerList = getAll(Worker.class);
                for (Worker worker : workerList) {
                    if (worker.getId() == ((Worker) ((Message) msg).getObject()).getId()) {
                        session.beginTransaction();
                        worker.setConnected(true);
                        session.save(worker);
                        session.flush();
                        session.getTransaction().commit();
                        break;
                    }
                }
                List<Complaint> complaintList = getAll(Complaint.class);
                List<Refund> refundList = getAll(Refund.class);
                List<NewPrice> newPriceList = getAll(NewPrice.class);
                client.sendToClient(new Message("#ShowAdminPage", ((Worker) ((Message) msg).getObject()), complaintList, refundList, newPriceList));
                session.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (msgString.startsWith("#UpdateWorkerState")) {
            try {
                session = sessionFactory.openSession();
                List<Worker> workerList = null;
                workerList = getAll(Worker.class);
                for (Worker worker : workerList) {
                    if (worker.getId() == ((Worker) ((Message) msg).getObject()).getId()) {
                        session.beginTransaction();
                        worker.setConnected(false);
                        session.save(worker);
                        session.flush();
                        session.getTransaction().commit();
                        break;
                    }
                }
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#UpdateComplaint")) {
            try {
                session = sessionFactory.openSession();
                List<Complaint> complaints = null;
                Complaint updatedComplaint = (Complaint) ((Message) msg).getObject();
                try {
                    complaints = getAll(Complaint.class);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                for (Complaint complaint : complaints) {
                    if (complaint.getCarNumber().equals(updatedComplaint.getCarNumber()) && complaint.getDescription().equals(updatedComplaint.getDescription())) {
                        session.beginTransaction();
                        if (updatedComplaint.getRefundValue() != null)
                            complaint.setRefundValue(updatedComplaint.getRefundValue());
                        if (!updatedComplaint.getResponse().equals(complaint.getResponse()))
                            complaint.setResponse(updatedComplaint.getResponse());
                        if (!updatedComplaint.getStatus().equals(complaint.getStatus()))
                            complaint.setStatus(updatedComplaint.getStatus());
                        session.save(complaint);
                        session.flush();
                        session.getTransaction().commit();
                    }
                }
                this.sendToAllClients(new Message("#RefreshComplaintList", complaints));
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ShowSubscribeRequest")) {
            try {
                session = sessionFactory.openSession();
                List<ParkingLot> parkingLots = getAll(ParkingLot.class);
                client.sendToClient(new Message("#ShowSubscribe", parkingLots));
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (msgString.startsWith("#AddSubscriberRequest")) {
            try {
                session = sessionFactory.openSession();
                SubsriptionClient subsriptionClient = (SubsriptionClient) ((Message) msg).getObject();
                session.beginTransaction();
                session.save(subsriptionClient);
                session.flush();
                session.getTransaction().commit();
                List<SubsriptionClient> subsriptionClients = getAll(SubsriptionClient.class);
                for (SubsriptionClient subsriptionClient1 : subsriptionClients) {
                    if (subsriptionClient.getDriverId().equals(subsriptionClient1.getDriverId()))
                        client.sendToClient(new Message(("#ShowSubscriptionID"), subsriptionClient1.getId()));
                }
                session.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#ShowComplaintRequest")) {
            try {
                client.sendToClient(new Message("#ShowComplaint"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#AddComplaintRequest")) {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Complaint complaint = (Complaint) ((Message) msg).getObject();
            session.save(complaint);
            session.flush();
            session.getTransaction().commit();
            List<Complaint> complaintList = null;
            while (complaintList == null) {
                try {
                    complaintList = getAll(Complaint.class);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            this.sendToAllClients(new Message("#RefreshComplaintList", complaintList));
            session.close();
        } else if (msgString.startsWith("#ShowAdminReserveParkingRequest")) {
            try {
                session = sessionFactory.openSession();
                Worker worker = (Worker) ((Message) msg).getObject();
                List<ParkingLot> parkingLotList;
                List<SubsriptionClient> subsriptionClients;
                parkingLotList = getAll(ParkingLot.class);
                subsriptionClients = getAll(SubsriptionClient.class);
                client.sendToClient(new Message("#ShowAdminReserveParking", parkingLotList, worker, subsriptionClients));
                session.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#ShowReportsRequest")) {
            try {
                session = sessionFactory.openSession();
                List<Reservation> reservationList = getAll(Reservation.class);
                List<Complaint> complaintList = getAll(Complaint.class);
                Worker worker = (Worker) ((Message) msg).getObject();
                List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
                client.sendToClient(new Message("#ShowReports", reservationList, complaintList, worker, parkingLotList));
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ShowCancelReservationReqeust")) {
            try {
                session = sessionFactory.openSession();
                List<Reservation> reservations = null;
                List<SubsriptionClient> subsriptionClients = null;
                List<Price> prices = null;
                reservations = getAll(Reservation.class);
                subsriptionClients = getAll(SubsriptionClient.class);
                prices = getAll(Price.class);
                client.sendToClient(new Message("#ShowCancelReservation", reservations, subsriptionClients, prices));
                session.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#CancelReservationRequest")) {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                Reservation reservation = (Reservation) ((Message) msg).getObject();
                SubsriptionClient subsriptionClient = (SubsriptionClient) ((Message) msg).getObject2();
                session.delete(reservation);
                if (subsriptionClient != null) {
                    try {
                        List<SubsriptionClient> subsriptionClientList = getAll(SubsriptionClient.class);
                        for (SubsriptionClient subsriptionClient1 : subsriptionClientList) {
                            if (subsriptionClient.getId() == subsriptionClient1.getId())
                                subsriptionClient1.setRemainingHours(subsriptionClient.getRemainingHours());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                session.flush();
                session.getTransaction().commit();
                List<Reservation> reservationList = getAll(Reservation.class);
                this.sendToAllClients(new Message("#RefreshReservationList", reservationList));
                session.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (msgString.startsWith("#ShowMyProfileRequest")) {
            try {
                session = sessionFactory.openSession();
                List<Reservation> reservationList = getAll(Reservation.class);
                List<SubsriptionClient> subsriptionClients = getAll(SubsriptionClient.class);
                client.sendToClient(new Message("#ShowMyProfile", reservationList, subsriptionClients));
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#ShowParkingLotStateRequest")) {
            try {
                session = sessionFactory.openSession();
                List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
                Worker worker = (Worker) ((Message) msg).getObject();
                client.sendToClient(new Message("#ShowParkingLotState", worker, parkingLotList));
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (msgString.startsWith("#updateParkingLotRequest")) {
            try {
                session = sessionFactory.openSession();
                ParkingLot choosenParkingLot = (ParkingLot) ((Message) msg).getObject();
                Spot choosenSpot = (Spot) ((Message) msg).getObject2();
                List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
                for (ParkingLot parkingLot : parkingLotList) {
                    if (choosenParkingLot.getId() == parkingLot.getId()) {
                        session.beginTransaction();
                        List<Spot> spotList = parkingLot.getParkingLotStatus();
                        for (Spot spot : spotList) {
                            if (spot.getDepthNum() == choosenSpot.getDepthNum() && spot.getFloorNum() == choosenSpot.getFloorNum() &&
                                    spot.getRowNum() == choosenSpot.getRowNum()) {
                                spot.setCar(choosenSpot.getCar());
                            }
                        }
                        session.update(parkingLot);
                        session.flush();
                        session.getTransaction().commit();
                    }
                }
                this.sendToAllClients(new Message("#RefreshParkingLots", parkingLotList));
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#UpdateNewPrice")) {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                NewPrice updatedNewPrice = (NewPrice) ((Message) msg).getObject();
                List<NewPrice> newPriceList = getAll(NewPrice.class);
                List<Price> priceList = getAll(Price.class);
                for (Price price : priceList) {
                    if (price.getId() == updatedNewPrice.getPriceId()) {
                        price.setPrice(updatedNewPrice.getNewPrice());
                        session.update(price);
                        session.flush();
                        break;
                    }
                }
                for (NewPrice newPrice : newPriceList) {
                    if (newPrice.getId() == updatedNewPrice.getId()) {
                        newPriceList.remove(newPrice);
                        session.delete(newPrice);
                        session.flush();
                        session.getTransaction().commit();
                        this.sendToAllClients(new Message("#RefreshPricesList", priceList,newPriceList));
                        session.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#RemoveNewPrice")) {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                NewPrice updatedNewPrice = (NewPrice) ((Message) msg).getObject();
                List<NewPrice> newPriceList = getAll(NewPrice.class);
                List<Price> priceList = getAll(Price.class);
                for (NewPrice newPrice : newPriceList) {
                    if (newPrice.getId() == updatedNewPrice.getId()) {
                        newPriceList.remove(newPrice);
                        session.delete(newPrice);
                        session.flush();
                        session.getTransaction().commit();
                        this.sendToAllClients(new Message("#RefreshPricesList", priceList,newPriceList));
                        session.close();
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(msgString.startsWith("#AddReserveredCarRequest")){
            try{
                session=sessionFactory.openSession();
                session.beginTransaction();
                List<Reservation> reservations=getAll(Reservation.class);
                Reservation reservation= (Reservation) ((Message)msg).getObject();
                List<ParkingLot> parkingLots=getAll(ParkingLot.class);
                List<Vehicle> vehicles=getAll(Vehicle.class);
                CarAddRemoveMethods.addReservationToPl(reservation,parkingLots,vehicles);
                for (ParkingLot parkingLot:parkingLots){
                    session.save(parkingLot);session.flush();
                }
                for (Vehicle vehicle:vehicles){
                    session.save(vehicle);session.flush();
                }
                for (Reservation reservation1:reservations){
                    if(reservation1.getId()==reservation1.getId()){
                        session.delete(reservation1);session.flush();
                        break;
                    }
                }
                this.sendToAllClients(new Message("#RefreshParkingLots", parkingLots));
                session.getTransaction().commit();
                session.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (msgString.startsWith("#RemoveVehicleRequset")) {
            try{
                session=sessionFactory.openSession();
                session.beginTransaction();
                List<ParkingLot> parkingLots=getAll(ParkingLot.class);
                List<Vehicle> vehicles=getAll(Vehicle.class);
                List<SubsriptionClient> subsriptionClients = (List<SubsriptionClient>) ((Message) msg).getObject2();
                Vehicle vehicle= (Vehicle) ((Message)msg).getObject();
                CarAddRemoveMethods.removeReservationFromPl(vehicle,parkingLots,vehicles);
                for (ParkingLot parkingLot:parkingLots){
                    session.save(parkingLot);session.flush();
                }
                for (Vehicle vehicle1:vehicles){
                    if(vehicle1.getId()==vehicle.getId()){
                        session.delete(vehicle1);session.flush();
                        break;
                    }
                }
                for(SubsriptionClient subsriptionClient: subsriptionClients)
                {
                    session.save(subsriptionClient);
                    session.flush();
                }
                this.sendToAllClients(new Message("#RefreshParkingLots", parkingLots));
                session.getTransaction().commit();
                session.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }






















    public static <T> ArrayList<T> getAll(Class<T> object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(object);
        query.from(object);
        ArrayList<T> data = (ArrayList<T>) session.createQuery(query).getResultList();
        return data;
    }
    public void generateParkingLots() {
        ParkingLot parkingLot1 = new ParkingLot(3, 5, 3);
        session.save(parkingLot1);
        session.flush();
        ParkingLot parkingLot2 = new ParkingLot(3, 4, 3);
        session.save(parkingLot2);
        session.flush();
        ParkingLot parkingLot3 = new ParkingLot(3, 7, 3);
        session.save(parkingLot3);
        session.flush();
    }

    public void generatePrices() {
        Random random = new Random();
        Price price1 = new Price("Occasional parking", "Per Hour", "8₪");
        session.save(price1);
        session.flush();
        Price price2 = new Price("Requested One-time parking", "Per Hour", "7₪");
        session.save(price2);
        session.flush();
        Price price3 = new Price("Regular monthly subscription-1 Car", "Fixed price", "60 hour of Requested One-time parking");
        session.save(price3);
        session.flush();
        Price price4 = new Price("Regular monthly subscription-Multi cars", "Fixed price", "54 hour of Requested One-time parking * Number of cars");
        session.save(price4);
        session.flush();
        Price price5 = new Price("Full monthly subscription-1 Car", "Fixed price", "72 hour of Requested One-time parking");
        session.save(price5);
        session.flush();
    }

    public void generateWorkers() throws NoSuchAlgorithmException {
        Worker ParkingLotWorker = new Worker();
        ParkingLotWorker.setEmail("ParkingLotWorker@hotmail.com");
        ParkingLotWorker.setWorkerId("2");
        ParkingLotWorker.setLastName("Hassan");
        ParkingLotWorker.setFirstName("Mahmoud");
        ParkingLotWorker.setOccupation("Parking Lot Worker");
        ParkingLotWorker.setPassword("admin");
        session.save(ParkingLotWorker);
        session.flush();
        Worker ParkingLotManager = new Worker();
        ParkingLotManager.setEmail("ParkingLotManager@hotmail.com");
        ParkingLotManager.setWorkerId("2");
        ParkingLotManager.setLastName("Khalil");
        ParkingLotManager.setFirstName("Mohammed");
        ParkingLotManager.setOccupation("Parking Lot Manager");
        ParkingLotManager.setPassword("admin");
        session.save(ParkingLotManager);
        session.flush();
        Worker ChainManager = new Worker();
        ChainManager.setEmail("ChainManager@hotmail.com");
        ChainManager.setWorkerId("2");
        ChainManager.setLastName("Kais");
        ChainManager.setFirstName("Ahmad");
        ChainManager.setOccupation("Chain Manager");
        ChainManager.setPassword("admin");
        session.save(ChainManager);
        session.flush();
        Worker CustomerService = new Worker();
        CustomerService.setEmail("CustomerService@hotmail.com");
        CustomerService.setWorkerId("2");
        CustomerService.setLastName("Shalabe");
        CustomerService.setFirstName("Rojeh");
        CustomerService.setOccupation("Customer Service");
        CustomerService.setPassword("admin");
        session.save(CustomerService);
        session.flush();
    }

    public void generateRefunds() {
        Refund refund1 = new Refund("up to no less than three hours before the parking start time", "%90");
        Refund refund2 = new Refund("Between three hours and one hour before the start of parking", "%50");
        Refund refund3 = new Refund("The last hour before parking starts", "%10");
        session.save(refund1);
        session.flush();
        session.save(refund2);
        session.flush();
        session.save(refund3);
        session.flush();

    }

    private static void addWorkerToParkingLot() throws Exception {
        List<Worker> workers = getAll(Worker.class);
        List<ParkingLot> parkingLots = getAll(ParkingLot.class);

        for (Worker worker : workers) {
            Random random = new Random();
            int val = random.nextInt(parkingLots.size());
            ParkingLot tempparkinglot = parkingLots.get(val);
            if (worker.getOccupation().startsWith("Parking Lot Worker")) {
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

    public void Connectdata() {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            generateParkingLots();
            generatePrices();
            generateWorkers();
            generateRefunds();

            addWorkerToParkingLot();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        //timer.scheduleAtFixedRate(scheduledTask,0, (1000)*3);
    }

    private boolean canBeAdded(Object object) {
        try {
            int totalSpaces = 0;
            int id;
            LocalDateTime arrival;
            LocalDateTime depature;
            if (object.getClass().equals(Reservation.class)) {
                id = ((Reservation) object).getParkingLotID();
                arrival = ((Reservation) object).getTimeOfArrival();
                depature = ((Reservation) object).getTimeOfDeparture();
            } else if (object.getClass().equals(Spot.class) && ((Spot) object).getCar().getClass().equals(Vehicle.class)) {
                id = ((Vehicle) (((Spot) object).getCar())).getId();
                arrival = ((Vehicle) (((Spot) object).getCar())).getTimeOfArrival();
                depature = ((Vehicle) (((Spot) object).getCar())).getTimeOfDeparture();

            } else {
                return false;
            }
            List<Reservation> reservationList = getAll(Reservation.class);
            List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
            for (ParkingLot parkingLot : parkingLotList) {
                if (parkingLot.getId() == id) {
                    List<Spot> array = parkingLot.getParkingLotStatus();
                    for (Object state : array) {
                        if (state.getClass().equals(Spot.class) && ((Spot) state).getCar().getClass().equals(Vehicle.class)) {
                            Vehicle vehicle = ((Vehicle) (((Spot) state).getCar()));
                            if (vehicle.getTimeOfDeparture().isBefore(arrival) || depature.isBefore(vehicle.getTimeOfArrival())) {
                                totalSpaces += 1;
                            }
                        }
                        if (((Spot) state).getCar().equals("Open")) {
                            totalSpaces += 1;
                        }
                    }
                    break;
                }
            }
            for (Reservation reservationelement : reservationList) {
                if (!(reservationelement.getTimeOfDeparture().isBefore(arrival) || depature.isBefore(reservationelement.getTimeOfArrival()))) {
                    totalSpaces -= 1;
                }
            }
            return (totalSpaces > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(ParkingLot.class);
        configuration.addAnnotatedClass(Price.class);
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Vehicle.class);
        configuration.addAnnotatedClass(Spot.class);
        configuration.addAnnotatedClass(Complaint.class);
        configuration.addAnnotatedClass(Refund.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(SubsriptionClient.class);
        configuration.addAnnotatedClass(NewPrice.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
