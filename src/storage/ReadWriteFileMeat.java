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

public class ReadWriteFileMeat implements IReadWriteFileMeat {
    public static final String PATHNAME = "/src/file/meat.txt";

    public List<Meat> readFile() {
        File file = new File(PATHNAME);
        if (!file.exists()) {
            return getDefaultMeats();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Meat>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            return getDefaultMeats();
        }
    }

    public void writeFile(List<Meat> meats) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATHNAME))) {
            oos.writeObject(meats);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    private List<Meat> getDefaultMeats() {
        List<Meat> defaultMeats = new ArrayList<>();

        defaultMeats.add(new Meat("01", "bò", 200, 10, LocalDate.now(),10, Sales.SALE10));
        defaultMeats.add(new Meat("02", "lợn", 160, 10, LocalDate.now(),10, Sales.SALE15));
        defaultMeats.add(new Meat("03", "gà", 150, 10, LocalDate.now(),10, Sales.SALE20));

        writeFile(defaultMeats);
        return defaultMeats;
    }
}
