package product;

import format.Sales;

import java.io.Serializable;
import java.time.LocalDate;

public class Meat extends Food implements Discount, Serializable {
    private double weight;

    public Meat() {
    }

    public Meat(String id, String name, double price, int quantity, LocalDate date, double weight, Sales sale) {
        super(id, name, price, quantity, date, sale);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getRealMoney() {
        return getPrice()*weight*getQuantity()*getSale().getPercent();
    }
}
