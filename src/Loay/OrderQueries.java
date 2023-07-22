package Loay;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class OrderQueries extends Queries{

    private PreparedStatement selectAllOrders;
    private PreparedStatement insertNewOrder;


    public OrderQueries(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            selectAllOrders = connection.prepareStatement("SELECT * from orders");
            insertNewOrder = connection.prepareStatement("INSERT INTO orders (C_ID, order_date, totalPrice) VALUES (? , ?, ?)",Statement.RETURN_GENERATED_KEYS);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Order> selectAllOrders(){
        List<Order> orders = null;
        ResultSet resultSet = null;
        try {
            resultSet = selectAllOrders.executeQuery();
            orders= new ArrayList<Order>();
            while (resultSet.next()){
                OrderDetailsQueries q =new OrderDetailsQueries();
                double totalPrice= q.getTotalPrice(resultSet.getString("Order_ID"));
                orders.add(new Order(
                        resultSet.getString("Order_ID"),
                        resultSet.getString("C_ID"),
                        resultSet.getString("order_date"),
                        totalPrice));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return orders;
    }

    public String insertNewOrder(String clientID, double totalPrice){
        LocalDate localDate = LocalDate.now();
        String newOrderId="";
        try {
            insertNewOrder.setString(1, clientID);
            insertNewOrder.setString(2, localDate.toString());
            insertNewOrder.setString(3, String.valueOf(totalPrice));

            insertNewOrder.executeUpdate();
            ResultSet rs = insertNewOrder.getGeneratedKeys();
            if(rs.next())
                newOrderId= rs.getString(1);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return newOrderId;
    }

}