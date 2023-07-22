package Loay;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class OrderDetailsQueries extends Queries{

    private PreparedStatement getOrderDetails;
    private PreparedStatement insertOrderDetails;

    public OrderDetailsQueries(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            getOrderDetails = connection.prepareStatement("SELECT * FROM order_details WHERE Order_ID = ?");
            insertOrderDetails= connection.prepareStatement("INSERT INTO order_details (Order_ID, M_ID, quantity) VALUES ( ? , ? , ? )");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public List<OrderDetails> getOrderDetails(String orderID){
        List<OrderDetails> orderDetails = null;
        try {
            getOrderDetails.setString(1, orderID);
            ResultSet resultSet= getOrderDetails.executeQuery();
            orderDetails = new ArrayList<OrderDetails>();
            while (resultSet.next()){
                orderDetails.add(new OrderDetails(
                        resultSet.getString("Order_ID"),
                        resultSet.getString("M_ID"),
                        resultSet.getInt("quantity")));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return orderDetails;
    }

    public double getTotalPrice(String orderID){
        double total =0;
        try {
            getOrderDetails.setString(1, orderID);
            ResultSet resultSet = getOrderDetails.executeQuery();
            while (resultSet.next()){
                MealQueries q = new MealQueries();
                total += q.selectMeal(resultSet.getString("M_ID")).getPrice() * resultSet.getInt("quantity");
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return total;
    }

    public void insertOrderDetails(String orderID, String mealID, int quantity){
        try {
            insertOrderDetails.setString(1, orderID);
            insertOrderDetails.setString(2, mealID);
            insertOrderDetails.setString(3, String.valueOf(quantity));

            insertOrderDetails.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
