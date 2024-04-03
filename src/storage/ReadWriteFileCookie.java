package storage;

import format.CookieSize;
import format.Sales;
import product.Cookie;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFileCookie implements IReadWriteFileCookie {
    public static final String PATHNAME = "/src/file/cookie.txt";

    public List<Cookie> readFile() {
        File file = new File(PATHNAME);
        if (!file.exists()) {
            return getDefaultCookies();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Cookie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            return getDefaultCookies();
        }
    }

    public void writeFile(List<Cookie> cookies) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATHNAME))) {
            oos.writeObject(cookies);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    private List<Cookie> getDefaultCookies() {
        List<Cookie> defaultCookies = new ArrayList<>();

        defaultCookies.add(new Cookie("04", "bánh mỳ", 30, 15, LocalDate.now(), Sales.SALE10, CookieSize.SMALLSIZE));
        defaultCookies.add(new Cookie("05", "gato", 50, 20, LocalDate.now(), Sales.SALE10, CookieSize.BIGSIZE));
        defaultCookies.add(new Cookie("06", "su kem", 25, 15, LocalDate.now(), Sales.SALE10, CookieSize.SMALLSIZE));

        writeFile(defaultCookies);
        return defaultCookies;
    }
}
