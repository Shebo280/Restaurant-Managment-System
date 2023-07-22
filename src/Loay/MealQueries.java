package Loay;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class MealQueries extends Queries{

    private PreparedStatement getAllMeals;
    private PreparedStatement selectMeal;
    private PreparedStatement insertNewMeal;
    private PreparedStatement removeMeal;
    private PreparedStatement updateMealName;
    private PreparedStatement updateMealPrice;
    private PreparedStatement updateMealQuantity;
    private PreparedStatement restId;

    public MealQueries(){
        try{

            Connection connection = DriverManager.getConnection(url, username, password);
            getAllMeals= connection.prepareStatement("SELECT * FROM meals");
            selectMeal = connection.prepareStatement("SELECT * FROM meals WHERE M_ID= ? ");
            insertNewMeal= connection.prepareStatement("INSERT INTO meals (meal, price, quantity) VALUES ( ?, ?, ?)");
            removeMeal= connection.prepareStatement("DELETE FROM meals WHERE M_ID = ?");
            updateMealName= connection.prepareStatement("UPDATE meals SET meal = ? WHERE M_ID= ?");
            updateMealPrice= connection.prepareStatement("UPDATE meals SET price = ? WHERE M_ID= ?");
            updateMealQuantity = connection.prepareStatement("UPDATE meals SET quantity = ? WHERE quantity= ?");
            restId= connection.prepareStatement("alter table meals AUTO_INCREMENT=1");

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Meal> getAllMeals(){
        List<Meal> meals = null;
        try{
            ResultSet resultSet= getAllMeals.executeQuery();
            meals= new ArrayList<Meal>();
            while(resultSet.next()){
                meals.add(new Meal(resultSet.getString("M_ID"),
                        resultSet.getString("meal"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("imgSrc")));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
        return meals;
    }

    public Meal selectMeal(String id){
        Meal meal = null;
        try {
            selectMeal.setString(1, id);
            ResultSet resultSet= selectMeal.executeQuery();
            if(resultSet.next()){
                meal= new Meal(resultSet.getString("M_ID"),
                        resultSet.getString("meal"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("imgSrc"));
            }
            else{
                JOptionPane.showMessageDialog(null, "Meal Not Found, Try again", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage() ,"Database connection error", JOptionPane.INFORMATION_MESSAGE);

        }
        return meal;
    }

    public void removeMeal(String id){
        try {
            removeMeal.setString(1, id);
            int result= removeMeal.executeUpdate();
            if(result!=0)    {
                JOptionPane.showMessageDialog(null, "Meal Removed", "Done", JOptionPane.INFORMATION_MESSAGE);
                restId.executeUpdate();
            }
            else JOptionPane.showMessageDialog(null, "Meal Not Found, Try again", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertNewMeal(String mealName, double price, int quantity, String imgSrc){
        try{
            insertNewMeal.setString(1, mealName);
            insertNewMeal.setString(2, String.valueOf(price));
            insertNewMeal.setString(3, String.valueOf(quantity));
            Meal meal = new Meal(mealName, price, quantity, imgSrc);
            insertNewMeal.executeUpdate();
        }
        catch (IllegalArgumentException e){
            String s = e.toString().substring(e.toString().indexOf(":") + 1);
            JOptionPane.showMessageDialog(null, s, "Try again", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateData (String newData, String id, PreparedStatement statement){
        try{
            statement.setString(1, newData);
            statement.setString(2, id);
            int result= statement.executeUpdate();

            String s="";
            if (statement == updateMealName)  s= "Meal name";
            else if(statement == updateMealPrice) s="Meal price";
            else if (statement == updateMealQuantity) s="Meal quantity";

            if(result!=0)  JOptionPane.showMessageDialog(null, s + " updated to : "+ newData, "Done", JOptionPane.INFORMATION_MESSAGE);
            else  JOptionPane.showMessageDialog(null,"Not found in database", "error", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateMealName(String newName, String id){
        if(newName.isEmpty())
            JOptionPane.showMessageDialog(null, "Meal name can't be empty","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(newName, id, updateMealName);
    }

    public void updateMealPrice(double newPrice, String id){
        if(newPrice < 0.0 )
            JOptionPane.showMessageDialog(null, "Price can't be negative value","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(String.valueOf(newPrice), id, updateMealPrice);
    }

    public void updateMealQuantity(int newQuantity, String id){
        if(newQuantity < 0)
            JOptionPane.showMessageDialog(null, "Quantity can't be negative value","Error", JOptionPane.ERROR_MESSAGE);
        else updateData(String.valueOf(newQuantity), id, updateMealQuantity);
    }

    public void updateAllData(String newMeal, double newPrice, int newQuantity, String id){
        Meal currentMeal = selectMeal(id);
        if(!Objects.equals(currentMeal.getMeal(), newMeal))     updateMealName(newMeal, id);
        if(currentMeal.getPrice() != newPrice) updateMealPrice(newPrice, id);
        if(currentMeal.getQuantity() != newQuantity )   updateMealQuantity(newQuantity, id);
    }

}
