package product;

import java.io.Serializable;
import java.time.LocalDate;

public class Cookie extends Food implements Discount, Serializable {
    private final double bigSize = 2;
    private final double smallSize = 1.5;
    private double size;
    public Cookie() {
    }

    public Cookie(String id, String name, double price, int quantity, LocalDate date, double sale, double size) {
        super(id, name, price, quantity, date, sale);
        this.size = size;
    }

    @Override
    public double getRealMoney() {
        if(size == bigSize){
            return getPrice()*bigSize*getSale()*getQuantity();
        }
        else if (size == smallSize){
            return getPrice()*smallSize*getSale()*getQuantity();
        }
        else {
            return 0;
        }
    }


}
