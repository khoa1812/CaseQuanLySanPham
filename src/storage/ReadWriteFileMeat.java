package storage;


import format.Sales;
import product.Meat;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileMeat implements IReadWriteFileMeat {
    public static final String PATHNAMEMEAT = "meatfile.txt";

    public List<Meat> readFile() {
        File fileMeat = new File(PATHNAMEMEAT);
        if (!fileMeat.exists()) {
            return getDefaultMeats();
        }

        try (ObjectInputStream oisMeat = new ObjectInputStream(new FileInputStream(fileMeat))) {
            return (List<Meat>) oisMeat.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            return getDefaultMeats();
        }
    }

    public void writeFile(List<Meat> meats) {
        try (ObjectOutputStream oosMeat = new ObjectOutputStream(new FileOutputStream(PATHNAMEMEAT))) {
            oosMeat.writeObject(meats);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    private List<Meat> getDefaultMeats() {
        List<Meat> defaultMeats = new ArrayList<>();

        defaultMeats.add(new Meat("1", "bò", 200, 10, LocalDate.now(),10, Sales.SALE10));
        defaultMeats.add(new Meat("2", "lợn", 160, 10, LocalDate.now(),10, Sales.SALE15));
        defaultMeats.add(new Meat("3", "gà", 150, 10, LocalDate.now(),10, Sales.SALE20));

        writeFile(defaultMeats);
        return defaultMeats;
    }
}