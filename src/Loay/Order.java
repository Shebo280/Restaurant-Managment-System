package Loay;
public class Order {
    private String orderID;
    private final String clientID;
    private final String date;
    private final double totalPrice;

    public Order(String clientID,String date, double totalPrice){
        this.clientID = clientID;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Order(String orderID, String clientID, String date, double totalPrice) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getClientID() {
        return clientID;
    }

    public String getDate() {
        return date;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format("Order id: %s%nClient Id: %s%n", getOrderID(), getClientID());
    }
}