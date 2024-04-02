package storage;

import format.CookieSize;
import format.Sales;
import product.Cookie;
import product.Food;
import product.Meat;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFile implements IReadWriteFile {
    public static final String PATHNAME = "product.txt";

    public List<Food> readFile() {
        File file = new File(PATHNAME);
        if (!file.exists()) {
            return getDefaultFoods();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Food>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            return getDefaultFoods();
        }
    }

    public void writeFile(List<Food> foods) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATHNAME))) {
            oos.writeObject(foods);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    private List<Food> getDefaultFoods() {
        List<Food> defaultFoods = new ArrayList<>();

        defaultFoods.add(new Meat("01", "bò", 200, 10, LocalDate.now(),10, Sales.SALE10));
        defaultFoods.add(new Meat("02", "lợn", 160, 10, LocalDate.now(),10, Sales.SALE15));
        defaultFoods.add(new Meat("03", "gà", 150, 10, LocalDate.now(),10, Sales.SALE20));
        defaultFoods.add(new Cookie("04", "bánh mỳ", 30, 15, LocalDate.now(), Sales.SALE10, CookieSize.SMALLSIZE));
        defaultFoods.add(new Cookie("05", "gato", 50, 20, LocalDate.now(), Sales.SALE10, CookieSize.BIGSIZE));
        defaultFoods.add(new Cookie("06", "su kem", 25, 15, LocalDate.now(), Sales.SALE10, CookieSize.SMALLSIZE));

        writeFile(defaultFoods);
        return defaultFoods;
    }
}
