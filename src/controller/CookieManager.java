package controller;

import format.CookieSize;
import format.Regex;
import format.Sales;
import product.Cookie;
import storage.IReadWriteFileCookie;
import storage.ReadWriteFileCookie;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class CookieManager implements Regex {
    private static IReadWriteFileCookie readWriteFileCookie = new ReadWriteFileCookie();
    private static List<Cookie> cookies = readWriteFileCookie.readFile();

    public static void editCookie(String cookieId, Scanner scanner) {
        Cookie cookieToEdit = cookies.stream().filter(cookie -> cookie.getId().equals(cookieId)).findFirst().orElse(null);

        if (cookieToEdit == null) {
            System.out.println("Không tìm thấy sản phẩm với mã: " + cookieId);
            return;
        }

        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();
        cookieToEdit.setName(name);

        System.out.print("Nhập giá mới: ");
        double price = scanner.nextDouble();
        cookieToEdit.setPrice(price);

        System.out.print("Nhập số lượng mới: ");
        String quantityStr = scanner.next();
        while (!QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            System.err.println("Số lượng từ 1 đến 20.");
            System.out.print("Nhập số lượng mới: ");
            quantityStr = scanner.next();
        }
        int quantity = Integer.parseInt(quantityStr);
        cookieToEdit.setQuantity(quantity);
        scanner.nextLine();

        System.out.print("Chọn mức giảm giá (1 - SALE10, 2 - SALE15, 3 - SALE20): ");
        int saleOption = scanner.nextInt();
        scanner.nextLine();

        Sales sale = Sales.SALE10;
        switch (saleOption) {
            case 1:
                sale = Sales.SALE10;
                break;
            case 2:
                sale = Sales.SALE15;
                break;
            case 3:
                sale = Sales.SALE20;
                break;
            default:
                System.err.println("Lựa chọn không hợp lệ. Sử dụng mức giảm giá mặc định SALE10.");
                break;
        }
        cookieToEdit.setSale(sale);

        readWriteFileCookie.writeFile(cookies);
        System.out.println("Sản phẩm đã được cập nhật thành công.");
    }


    public static void addNewCookie(Scanner scanner) {
        System.out.print("Nhập id: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng: ");
        String quantityStr = scanner.nextLine();
        while (!QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            System.err.println("Số lượng từ 1 đến 20.");
            System.out.print("Nhập số lượng: ");
            quantityStr = scanner.nextLine();
        }
        int quantity = Integer.parseInt(quantityStr);
        System.out.print("Chọn kích cỡ (1 - BIGSIZE, 2 - SMALLSIZE): ");
        int sizeOption = scanner.nextInt();
        System.out.print("Chọn mức giảm giá (1 - SALE10, 2 - SALE15, 3 - SALE20): ");
        int saleOption = scanner.nextInt();
        scanner.nextLine();

        CookieSize size = (sizeOption == 1) ? CookieSize.BIGSIZE : CookieSize.SMALLSIZE;

        Sales sale = Sales.SALE10;
        switch (saleOption) {
            case 1:
                sale = Sales.SALE10;
                break;
            case 2:
                sale = Sales.SALE15;
                break;
            case 3:
                sale = Sales.SALE20;
                break;
            default:
                System.err.println("Lựa chọn không hợp lệ. Sử dụng mức giảm giá mặc định SALE10.");
                break;
        }

        Cookie cookie = new Cookie(id, name, price, quantity, LocalDate.now(), sale, size);
        cookies.add(cookie);
        readWriteFileCookie.writeFile(cookies);
        System.out.println("Sản phẩm cookie đã được thêm thành công.");
    }


    public static void displayCookies() {
        if (cookies.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
    }

    public static void findCookie(String cookieId) {
        for (Cookie cookie : cookies) {
            if (cookie.getId().equals(cookieId)) {
                System.out.println("Sản phẩm được tìm thấy: " + cookie);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: " + cookieId);
    }

    public static void removeCookie(String cookieId) {
        Cookie cookieToRemove = null;
        for (Cookie cookie : cookies) {
            if (cookie.getId().equals(cookieId)) {
                cookieToRemove = cookie;
                break;
            }
        }
        if (cookieToRemove != null) {
            cookies.remove(cookieToRemove);
            readWriteFileCookie.writeFile(cookies);
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + cookieId);
        }
    }

    public static void sortCookiesByPrice() {
        cookies.sort(Comparator.comparingDouble(Cookie::getPrice));
        displayCookies();
    }

    public static void sortCookiesByExpirationDate() {
        cookies.sort(Comparator.comparing(Cookie::getDate));
        displayCookies();
    }



}
