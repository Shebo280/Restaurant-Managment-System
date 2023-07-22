package Loay;
public class OrderDetails {
       
    private String orderID;
    private  String mealID;
    private  int quantity;
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public OrderDetails(){}
    public OrderDetails(String orderID, String mealID, int quantity) {
        this.orderID =orderID;
        this.mealID = mealID;
        this.quantity = quantity;
    }

    public OrderDetails(String mealID, int quantity){
        this.mealID = mealID;
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getMealID() {
        return mealID;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderID='" + orderID + '\'' +
                ", mealID='" + mealID + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
