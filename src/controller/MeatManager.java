package controller;

import format.Regex;
import format.Sales;
import product.Meat;
import storage.*;
import storage.ReadWriteFileMeat;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class MeatManager implements Regex {
    private static IReadWriteFileMeat readWriteFileMeat = new ReadWriteFileMeat();
    private static List<Meat> meats = readWriteFileMeat.readFile();

    public static void editMeat(String meatId, Scanner scanner) {
        Meat meatToEdit = meats.stream().filter(meat -> meat.getId().equals(meatId)).findFirst().orElse(null);

        if (meatToEdit == null) {
            System.out.println("Không tìm thấy sản phẩm với mã: " + meatId);
            return;
        }

        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();
        meatToEdit.setName(name);

        System.out.print("Nhập giá mới: ");
        double price = scanner.nextDouble();
        meatToEdit.setPrice(price);

        System.out.print("Nhập số lượng mới: ");
        String quantityStr = scanner.next();
        while (!QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            System.out.println("Số lượng từ 1 đến 20.");
            System.out.print("Nhập số lượng mới: ");
            quantityStr = scanner.next();
        }
        int quantity = Integer.parseInt(quantityStr);
        meatToEdit.setQuantity(quantity);
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
                System.out.println("Lựa chọn không hợp lệ. Sử dụng mức giảm giá mặc định SALE10.");
                break;
        }
        meatToEdit.setSale(sale);

        readWriteFileMeat.writeFile(meats);
        System.out.println("Sản phẩm đã được cập nhật thành công.");
    }


    public static void addNewMeat(Scanner scanner) {
        System.out.print("Nhập id: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng: ");
        String quantityStr = scanner.nextLine();
        while (!QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            System.out.println("Số lượng từ 1 đến 20.");
            System.out.print("Nhập số lượng: ");
            quantityStr = scanner.nextLine();
        }
        int quantity = Integer.parseInt(quantityStr);
        System.out.print("Nhập trọng lượng: ");
        double weight = scanner.nextDouble();
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
                System.out.println("Lựa chọn không hợp lệ. Sử dụng mức giảm giá mặc định SALE10.");
                break;
        }

        Meat meat = new Meat(id, name, price, quantity, LocalDate.now(), weight, sale);
        meats.add(meat);
        readWriteFileMeat.writeFile(meats);
        System.out.println("Sản phẩm thịt đã được thêm thành công.");
    }


    public static void displayMeats() {
        if (meats.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        for (Meat meat : meats) {
            System.out.println(meats);
        }
    }

    public static void findMeat(String cookieId) {
        for (Meat meat : meats) {
            if (meat.getId().equals(cookieId)) {
                System.out.println("Sản phẩm được tìm thấy: " + meat);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: " + cookieId);
    }

    public static void removeMeat(String meatId) {
        Meat meatToRemove = null;
        for (Meat meat : meats) {
            if (meat.getId().equals(meatId)) {
                meatToRemove = meat;
                break;
            }
        }
        if (meatToRemove != null) {
            meats.remove(meatToRemove);
            readWriteFileMeat.writeFile(meats);
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + meatId);
        }
    }

    public static void sortMeatsByPrice() {
        meats.sort(Comparator.comparingDouble(Meat::getPrice));
        displayMeats();
    }

    public static void sortMeatsByExpirationDate() {
        meats.sort(Comparator.comparing(Meat::getDate));
        displayMeats();
    }



}

