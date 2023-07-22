package Loay;
public class Meal {
    private String id;
    private final String meal;
    private final double price;
    private final int quantity;
    private final String imgSrc;

    public Meal(String meal, double price, int quantity, String imgSrc) {
        if(meal.equals("")) throw new IllegalArgumentException("Meal name can't be empty");
        if (price < 0.0) throw new IllegalArgumentException("Price can't be negative value");
        if (quantity< 0) throw new IllegalArgumentException("Quantity can't be negative value");

        this.quantity = quantity;
        this.price = price;
        this.meal = meal;
        this.imgSrc = imgSrc;
    }

    public Meal(String  id, String meal, double price, int quantity, String  imgSrc) {
        if(meal.equals("")) throw new IllegalArgumentException("Meal name can't be empty");
        if (price < 0.0) throw new IllegalArgumentException("Price can't be negative value");
        if (quantity< 0) throw new IllegalArgumentException("Quantity can't be negative value");

        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.meal = meal;
        this.imgSrc = imgSrc;
    }

    public String  getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getMeal() {
        return meal;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    @Override
    public String toString() {
        return String.format("Meal ID: %s%nMeal: %s%nPrice: $%,.2f%nQuantity: %d%n", getId(), getMeal(), getPrice(), getQuantity());
    }
}