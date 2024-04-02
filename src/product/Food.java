package product;

import java.time.LocalDate;

public class Food {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private LocalDate date;
    private double sale;

    public Food() {
    }

    public Food(String id, String name, double price, int quantity, LocalDate date, double sale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.sale = sale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", date=" + date +
                ", sale=" + sale +
                '}';
    }
}
