package controller;

import format.CookieSize;
import format.Regex;
import format.Sales;
import product.Cookie;
import product.Food;
import product.Meat;
import storage.IReadWriteFile;
import storage.ReadWriteFile;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class FoodManager implements Regex {
    private static IReadWriteFile readWriteFile = new ReadWriteFile();
    private static List<Food> foods = readWriteFile.readFile();

    public static void addNewFood(Scanner scanner) {
        System.out.print("Chọn loại sản phẩm để thêm (1 - Meat, 2 - Cookie): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        switch (type) {
            case 1:
                addNewMeat(scanner);
                break;
            case 2:
                addNewCookie(scanner);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                break;
        }
    }


//    public static void editFood(String foodId, Food updatedFood, Scanner scanner) {
//        int foodIndex = -1;
//        for (int i = 0; i < foods.size(); i++) {
//            if (foods.get(i).getId().equals(foodId)) {
//                foodIndex = i;
//                break;
//            }
//        }
//        if (foodIndex != -1) {
//            System.out.print("Chọn loại sản phẩm để thêm (1 - Meat, 2 - Cookie): ");
//            int type = scanner.nextInt();
//            scanner.nextLine();
//            switch (type) {
//                case 1:
//                    addNewMeat(scanner);
//                    break;
//                case 2:
//                    addNewCookie(scanner);
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//                    break;
//            }
//        } else {
//            System.out.println("Không tìm thấy sản phẩm với mã: " + foodId);
//        }
//    }

    public static void editFood(String foodId, Scanner scanner) {
        Food foodToEdit = foods.stream().filter(food -> food.getId().equals(foodId)).findFirst().orElse(null);

        if (foodToEdit == null) {
            System.out.println("Không tìm thấy sản phẩm với mã: " + foodId);
            return;
        }

        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();
        foodToEdit.setName(name);

        System.out.print("Nhập giá mới: ");
        double price = scanner.nextDouble();
        foodToEdit.setPrice(price);

        System.out.print("Nhập số lượng mới: ");
        String quantityStr = scanner.next();
        while (!QUANTITY_PATTERN.matcher(quantityStr).matches()) {
            System.out.println("Số lượng từ 1 đến 20.");
            System.out.print("Nhập số lượng mới: ");
            quantityStr = scanner.next();
        }
        int quantity = Integer.parseInt(quantityStr);
        foodToEdit.setQuantity(quantity);
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
        foodToEdit.setSale(sale);

        readWriteFile.writeFile(foods);
        System.out.println("Sản phẩm đã được cập nhật thành công.");
    }

    private static void addNewMeat(Scanner scanner) {
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
        foods.add(meat);
        readWriteFile.writeFile(foods);
        System.out.println("Sản phẩm thịt đã được thêm thành công.");
    }


    private static void addNewCookie(Scanner scanner) {
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
                System.out.println("Lựa chọn không hợp lệ. Sử dụng mức giảm giá mặc định SALE10.");
                break;
        }

        Cookie cookie = new Cookie(id, name, price, quantity, LocalDate.now(), sale, size);
        foods.add(cookie);
        readWriteFile.writeFile(foods);
        System.out.println("Sản phẩm cookie đã được thêm thành công.");
    }


    public static void displayFoods() {
        if (foods.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        for (Food food : foods) {
            System.out.println(food);
        }
    }

    public static void findFood(String foodId) {
        for (Food food : foods) {
            if (food.getId().equals(foodId)) {
                System.out.println("Sản phẩm được tìm thấy: " + food);
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: " + foodId);
    }

    public static void removeFood(String foodId) {
        Food foodToRemove = null;
        for (Food food : foods) {
            if (food.getId().equals(foodId)) {
                foodToRemove = food;
                break;
            }
        }
        if (foodToRemove != null) {
            foods.remove(foodToRemove);
            readWriteFile.writeFile(foods);
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + foodId);
        }
    }

    public static void sortFoodsByPrice() {
        foods.sort(Comparator.comparingDouble(Food::getPrice));
        displayFoods();
    }

    public static void sortFoodsByExpirationDate() {
        foods.sort(Comparator.comparing(Food::getDate));
        displayFoods();
    }



}
