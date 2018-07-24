package makkah.wadi.instapay.instapay;

public class User {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    private String phone_number;
    private Double Balance;

    public User( Double Balance , String phone_number , String name) {
       
        this.name = name;
        //this.id = id;
        this.phone_number = phone_number;
        this.Balance = Balance;
    }

    public User (){
        //empty
    }
    
    

    

    public String getuserName() {
        return name;
    }

    public String getPhone() {
        return phone_number;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setuserName(String userName) {
        this.name = userName;
    }

    public void setPhone(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setBalance(Double Balance) {
        this.Balance = Balance;
    }
}


