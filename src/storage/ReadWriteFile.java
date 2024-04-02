package storage;

import product.Cookie;
import product.Food;
import product.Meat;

import java.io.*;
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
//        defaultProducts.add(new Product("01", "Duy", 1000, "Hãng Samsung", "Chạy chậm, đơ lác"));
//        defaultProducts.add(new Product("02", "Hieu", 2000, "Hãng Apple", "Gầy ốm"));
//        defaultProducts.add(new Product("03", "Quan", 3000, "Hãng Dell", "Nhanh mượt"));
        defaultFoods.add(new Meat());
        defaultFoods.add(new Cookie());

        writeFile(defaultFoods);
        return defaultFoods;
    }
}
