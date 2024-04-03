package product;

import format.Sales;
import format.Regex;

import java.io.Serializable;
import java.time.LocalDate;

public class Food implements Regex, Serializable {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private LocalDate date;
    private Sales sale;

    public Food() {
    }

    public Food(String id, String name, double price, int quantity, LocalDate date, Sales sale) {
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

    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }

    public void setQuantityFromString(String quantityStr) {
        if (QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            this.quantity = Integer.parseInt(quantityStr);
        } else {
            throw new IllegalArgumentException("Số lượng từ 1 đến 20.");
        }
    }

    public void setDateFromString(String dateString) {
        if (DATE_PATTERN.matcher(dateString).matches()) {
            this.date = LocalDate.parse(dateString);
        } else {
            throw new IllegalArgumentException("Định dạng bắt buộc: YYYY-MM-DD.");
        }
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
