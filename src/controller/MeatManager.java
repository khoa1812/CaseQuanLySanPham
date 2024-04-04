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

        double price = -1;
        do {
            System.out.print("Nhập giá mới (phải là một số và lớn hơn 0): ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                if (price <= 0) {
                    System.out.println("Giá trị phải lớn hơn 0. Vui lòng nhập lại.");
                    price = -1;
                }
            } else {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập một số.");
                scanner.next();
            }
        } while (price <= 0);

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
        double price = -1;
        do {
            System.out.print("Nhập giá mới (phải là một số và lớn hơn 0): ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                if (price <= 0) {
                    System.out.println("Giá trị phải lớn hơn 0. Vui lòng nhập lại.");
                    price = -1;
                }
            } else {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập một số.");
                scanner.next();
            }
        } while (price <= 0);
        System.out.print("Nhập số lượng: ");
        String quantityStr = scanner.nextLine();
        while (!QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            System.out.println("Số lượng từ 1 đến 20.");
            System.out.print("Nhập số lượng: ");
            quantityStr = scanner.nextLine();
        }
        int quantity = Integer.parseInt(quantityStr);
        double weight = -1;
        do {
            System.out.print("Nhập khối lượng mới (phải là một số và lớn hơn 0): ");
            if (scanner.hasNextDouble()) {
                weight = scanner.nextDouble();
                if (weight <= 0) {
                    System.out.println("Giá trị phải lớn hơn 0. Vui lòng nhập lại.");
                    weight = -1;
                }
            } else {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập một số.");
                scanner.next();
            }
        } while (weight <= 0);
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
            System.out.printf("%-10s %-20s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                    "Mã ID: " + meat.getId(),
                    "| Tên sản phẩm: " + meat.getName(),
                    "| Giá: " + String.format("%.2f", meat.getPrice()),
                    "| Số lượng: " + meat.getQuantity(),
                    "| Ngày nhập: " + meat.getDate(),
                    "| Trọng lượng " + String.format("%.2f", meat.getWeight()),
                    "| Giảm giá: " + meat.getSale(),
                    "| Giá khi giảm: " + String.format("%.2f", meat.getRealMoney()));
        }
    }

    public static void findMeat(String meatId) {
        for (Meat meat : meats) {
            if (meat.getId().equals(meatId)) {
                System.out.println("Sản phẩm được tìm thấy: " + meat);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: " + meatId);
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

    public static void sortMeatsByMoney() {
        meats.sort(Comparator.comparing(Meat::getRealMoney));
        displayMeats();
    }

}