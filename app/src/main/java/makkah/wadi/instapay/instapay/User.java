package makkah.wadi.instapay.instapay;

import java.util.ArrayList;

public class User {

    private String id;
    private String name;

    public User(String name, ArrayList<String> friendlist, String type, String phone_number, Double balance) {
        this.name = name;
        this.friendlist = friendlist;
        this.type = type;
        this.phone_number = phone_number;
        Balance = balance;
    }

    public ArrayList<String> getFriendlist() {
        return friendlist;
    }

    public void setFriendlist(ArrayList<String> friendlist) {
        this.friendlist = friendlist;
    }

    private ArrayList<String> friendlist ;
    public User( String name, String type, String phone_number, Double balance) {

        this.name = name;
        this.type = type;
        this.phone_number = phone_number;
        Balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    private String phone_number;
    private double Balance;


    public User (){
        //empty
    }
    
    

    

    public String getuserName() {
        return name;
    }

    public String getPhone() {
        return phone_number;
    }

    public double getBalance() {
        return Balance;
    }

    public void setuserName(String userName) {
        this.name = userName;
    }

    public void setPhone(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
}


