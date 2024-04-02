package view;

import product.Cookie;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cookie c = new Cookie("1", "gối", 100, 10, LocalDate.now(), 0.9, 1.5);
        System.out.println(c.getRealMoney());
    }
}
