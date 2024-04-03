package product;

import format.CookieSize;
import format.Sales;

import java.io.Serializable;
import java.time.LocalDate;

public class Cookie extends Food implements Discount, Serializable {
    private CookieSize size;
    public Cookie() {
    }

    public Cookie(String id, String name, double price, int quantity, LocalDate date, Sales sale, CookieSize size) {
        super(id, name, price, quantity, date, sale);
        this.size = size;
    }

    public CookieSize getSize() {
        return size;
    }

    public void setSize(CookieSize size) {
        this.size = size;
    }

    @Override
    public double getRealMoney() {
        if (size != null) {
            return getPrice() * size.getValue() * getSale().getPercent() * getQuantity();
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return "Cookie{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                ", date=" + getDate() +
                ", sale=" + getSale() +
                "size=" + size +
                '}';
    }

}
