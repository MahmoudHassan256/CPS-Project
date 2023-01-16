package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String WorkerId;
    private String FirstName;
    private String LastName;

    private String Email;
    private String Occupation;

    private boolean isConnected;
    @ManyToOne(optional = true)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", WorkerId='" + WorkerId + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Occupation='" + Occupation + '\'' +
                ", isConnected=" + isConnected +
                ", parkingLot=" + parkingLot +
                ", Password='" + Password + '\'' +
                '}';
    }

    private String Password;
    private String hashPassword(String Password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash=md.digest(Password.getBytes());
        StringBuilder sb=new StringBuilder();
        for (byte b:hash){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setWorkerId(String workerId) {
        WorkerId = workerId;
    }

    public String getWorkerId() {
        return WorkerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Worker() {
    }

    public Worker(String workerId,String firstName, String lastName, String email,String password, String occupation) throws NoSuchAlgorithmException {
        WorkerId=workerId;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Occupation = occupation;
        Password=hashPassword(password);
        isConnected=false;
    }
    public String getName(){
        String[] words = {getFirstName(),getLastName()};
        for (int i = 0; i < words.length; i++) {
            char first = words[i].charAt(0);
            words[i] = Character.toUpperCase(first) + words[i].substring(1);
        }
        String title = String.join(" ", words);
        return title;
    }
    public void setPassword(String password) throws NoSuchAlgorithmException {
        Password = hashPassword(password);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getPassword() {
        return Password;
    }
}
