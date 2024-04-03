package view;

import controller.FoodManager;
import format.Sales;
import product.Cookie;
import format.CookieSize;
import product.Food;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị tất cả sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm theo tên");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sửa thông tin sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá từ thấp đến cao");
            System.out.println("7. Sắp xếp sản phẩm theo ngày nhập");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    FoodManager.addNewFood(scanner);
                    break;
                case 2:
                    System.out.println("\nDanh sách sản phẩm:");
                    FoodManager.displayFoods();
                    break;
                case 3:
                    System.out.print("Nhập mã sản phẩm cần tìm theo id: ");
                    String foodId = scanner.nextLine();
                    FoodManager.findFood(foodId);
                    break;
                case 4:
                    System.out.println("Nhập mã sản phẩm cần xóa: ");
                    String foodId1 = scanner.nextLine();
                    FoodManager.removeFood(foodId1);
                    break;
                case 5:
                    System.out.print("Nhập mã sản phẩm cần sửa: ");
                    String foodIdToEdit = scanner.nextLine();
                    FoodManager.editFood(foodIdToEdit, scanner);
                    break;
                case 6:
                    FoodManager.sortFoodsByPrice();
                    break;
                case 7:
                    FoodManager.sortFoodsByExpirationDate();
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                    break;
            }
        } while (choice != 0);

        scanner.close();


    }

}

