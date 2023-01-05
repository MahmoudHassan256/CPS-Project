package il.cshaifasweng.OCSFMediatorExample.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    private String Type;//Subscription or oneTimer
    private ArrayList<Car> CarList;

    private String hashPassword(String Password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash=md.digest(Password.getBytes());
        return md.digest(Password.getBytes()).toString();
    }
    public Client(String firstName, String lastName, String email, String password, String type) throws NoSuchAlgorithmException {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = hashPassword(password);
        Type = type;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getType() {
        return Type;
    }

    public ArrayList<Car> getCarList() {
        return CarList;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setType(String type) {
        Type = type;
    }

    public Client() {
    }
    public void addCar(Car car){
        if(CarList==null){
            CarList=new ArrayList<>();
        }
        CarList.add(car);
    }
}
