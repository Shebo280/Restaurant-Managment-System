package Loay;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ClientQueries extends Queries {

    private PreparedStatement selectAllClients;
    private PreparedStatement selectClient;
    private PreparedStatement insertNewClient;
    private PreparedStatement authenticatedUser;
    private PreparedStatement getClientOrders;
    private PreparedStatement restID;

    private PreparedStatement removeClientByID;
    private PreparedStatement updateFirstNameByID;
    private PreparedStatement updateLastNameByID;
    private PreparedStatement updatePhoneNumberByID;
    private PreparedStatement updateEmailAddressByID;
    private PreparedStatement updatePasswordByID;

    public ClientQueries() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            selectAllClients = connection.prepareStatement("SELECT * FROM clients");
            selectClient = connection.prepareStatement("SELECT * FROM clients WHERE C_ID= ? ");
            authenticatedUser = connection.prepareStatement("SELECT * FROM clients WHERE email= ? AND Password= ?");
            insertNewClient = connection.prepareStatement("INSERT INTO clients (Fname, Lname, email, Phone_num, Password) VALUES ( ?, ?, ?, ?, ?)");
            removeClientByID = connection.prepareStatement("DELETE FROM clients WHERE C_ID = ?");
            updateFirstNameByID = connection.prepareStatement("UPDATE clients SET Fname = ? WHERE C_ID= ? ");
            updateLastNameByID = connection.prepareStatement("UPDATE clients SET Lname = ? WHERE C_ID= ? ");
            updateEmailAddressByID = connection.prepareStatement("UPDATE clients SET email = ? WHERE C_ID= ? ");
            updatePhoneNumberByID = connection.prepareStatement("UPDATE clients SET Phone_num = ? WHERE C_ID= ? ");
            updatePasswordByID = connection.prepareStatement("UPDATE clients SET Password = ? WHERE C_ID= ? ");
            getClientOrders= connection.prepareStatement("SELECT * FROM orders WHERE C_ID = ? ");
            restID = connection.prepareStatement("alter table clients AUTO_INCREMENT=1");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = null;
        ResultSet resultSet = null;
        try {
            resultSet = selectAllClients.executeQuery();
            clients = new ArrayList<Client>();
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getString("C_ID"),
                        resultSet.getString("Fname"),
                        resultSet.getString("Lname"),
                        resultSet.getString("email"),
                        resultSet.getString("Phone_Num"),
                        resultSet.getString("Password")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return clients;
    }

    public Client AuthenticatedUser(String email, String password) {
        Client client = null;
        try {
            authenticatedUser.setString(1, email);
            authenticatedUser.setString(2, password);
            ResultSet resultSet = authenticatedUser.executeQuery();

            if (resultSet.next()) {
                client = new Client(resultSet.getString("C_ID"),
                        resultSet.getString("Fname"),
                        resultSet.getString("Lname"),
                        resultSet.getString("email"),
                        resultSet.getString("Phone_num"),
                        resultSet.getString("Password"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return client;
    }

    public List<Order>  getClientOrders(String id){
        List<Order> orders = null;
        try {
            getClientOrders.setString(1, id);
            orders = new ArrayList<Order>();
            ResultSet resultSet= getClientOrders.executeQuery();
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

    public Client insertNewClient(String firstName, String lastName, String email, String phoneNum, String password) {
        Client client = null;
        boolean statue = false;
        try {
            client = new Client(firstName, lastName, email, phoneNum, password);
            insertNewClient.setString(1, firstName);
            insertNewClient.setString(2, lastName);
            insertNewClient.setString(3, email);
            insertNewClient.setString(4, phoneNum);
            insertNewClient.setString(5, password);
            insertNewClient.executeUpdate();
            JOptionPane.showMessageDialog(null, "Client Added", "Done", JOptionPane.INFORMATION_MESSAGE);
            statue = true;
        }
        catch (IllegalArgumentException e) {
            String s = e.toString().substring(e.toString().indexOf(":") + 1);
            JOptionPane.showMessageDialog(null, s, "Try again", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return statue ? client : null;
    }

    public Client selectClient(String id) {
        Client client = null;
        try {
            selectClient.setString(1, id);
            ResultSet resultSet = selectClient.executeQuery();
            if(resultSet.next()){
                client = new Client(resultSet.getString("C_ID"),
                        resultSet.getString("Fname"),
                        resultSet.getString("Lname"),
                        resultSet.getString("email"),
                        resultSet.getString("Phone_Num"),
                        resultSet.getString("Password"));
            }else{
                JOptionPane.showMessageDialog(null, "Client Not Found, Try again", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() ,"Database connection error", JOptionPane.INFORMATION_MESSAGE);
        }
        return client;
    }

    public void removeClient(String id) {
        try {
            removeClientByID.setString(1, id);
            int result = removeClientByID.executeUpdate();
            if(result !=0) {
                JOptionPane.showMessageDialog(null, "Client Removed", "Done", JOptionPane.INFORMATION_MESSAGE);
                restID.executeUpdate();
            }
            else JOptionPane.showMessageDialog(null, "Client Not Found, Try again", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData(String newData, String id, PreparedStatement statement) {
        try{
            statement.setString(1, newData);
            statement.setString(2, id);
            int result= statement.executeUpdate();

            String s="";
            if (statement == updateFirstNameByID)  s= "First name";
            else if(statement == updateLastNameByID) s="Last name";
            else if(statement== updatePhoneNumberByID) s="Phone number";
            else if (statement == updateEmailAddressByID) s= "Email address";
            else if(statement == updatePasswordByID) s= "Password";

            if(result!=0)   JOptionPane.showMessageDialog(null, s + " updated to : "+ newData, "Done", JOptionPane.INFORMATION_MESSAGE);
            else  JOptionPane.showMessageDialog(null,"Not found in database", "error", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateFirstName(String newFirstName, String id){
        if (newFirstName.isEmpty())
            JOptionPane.showMessageDialog(null, "First Name can't be empty","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(newFirstName, id, updateFirstNameByID);
    }

    public void updateLastName(String newLastName, String id){
        if (newLastName.isEmpty())
            JOptionPane.showMessageDialog(null, "Last Name can't be empty","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(newLastName, id, updateLastNameByID);
    }

    public void updateEmailAddress(String newEmail, String id){
        if (newEmail.isEmpty())
            JOptionPane.showMessageDialog(null, "Email can't be empty","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(newEmail, id, updateEmailAddressByID);
    }

    public void updatePhoneNumber(String newPhone, String id){
        boolean flag = true;
        for (int i = 0; i < newPhone.length(); i++) {
            if(newPhone.charAt(i) > '9' || newPhone.charAt(i) < '0'){
                flag = false;
                break;
            }
        }
        if (newPhone.isEmpty())
            JOptionPane.showMessageDialog(null, "Phone Number can't be empty","Error", JOptionPane.ERROR_MESSAGE);
        else if(!flag)
            JOptionPane.showMessageDialog(null, "Phone Number can't contain chars","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(newPhone, id, updatePhoneNumberByID);
    }

    public void updatePassword(String newPassword, String id){
        if (newPassword.isEmpty())
            JOptionPane.showMessageDialog(null, "Password can't be empty","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(newPassword, id, updatePasswordByID);
    }

    public void updateAllData(String newFirstName, String newLastName, String newEmail, String newPhone, String newPassword, String id){
        Client current = selectClient(id);
        if(!Objects.equals(current.getFirstName(), newFirstName)) updateFirstName(newFirstName, id);
        if(!Objects.equals(current.getLastName(), newLastName)) updateLastName(newLastName, id);
        if(!Objects.equals(current.getEmail(), newEmail))updateEmailAddress(newEmail, id);
        if(!Objects.equals(current.getPhoneNum(), newPhone)) updatePhoneNumber(newPhone, id);
        if(!Objects.equals(current.getPassword(), newPassword)) updatePassword(newPassword, id);
    }


}