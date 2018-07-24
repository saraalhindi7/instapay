package makkah.wadi.instapay.instapay;

public class Friendinfo {
    String Friend_name;
    String Friend_transaction;
    String QR_code ;

    public Friendinfo(String friend_name, String friend_transaction, String QR_code) {
        Friend_name = friend_name;
        Friend_transaction = friend_transaction;
        this.QR_code = QR_code;
    }

    public String getFriend_name() {
        return Friend_name;
    }

    public void setFriend_name(String friend_name) {
        Friend_name = friend_name;
    }

    public String getFriend_transaction() {
        return Friend_transaction;
    }

    public void setFriend_transaction(String friend_transaction) {
        Friend_transaction = friend_transaction;
    }

    public String getQR_code() {
        return QR_code;
    }

    public void setQR_code(String QR_code) {
        this.QR_code = QR_code;
    }
}
