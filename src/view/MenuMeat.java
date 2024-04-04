package view;

import controller.CookieManager;
import controller.MeatManager;

import java.util.Scanner;

public class MenuMeat {
    public static void showManuMeat() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n\t------------------ Menu -------------------");
            System.out.println("1.  |            Thêm sản phẩm mới            |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("2.  |         Hiển thị tất cả sản phẩm        |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("3.  |         Tìm kiếm sản phẩm theo id       |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("4.  |              Xóa sản phẩm               |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("5.  |         Sửa thông tin sản phẩm          |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("6.  |Sắp xếp sản phẩm theo giá từ thấp đến cao|");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("7.  |     Sắp xếp sản phẩm theo ngày nhập     |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("8.  |   Sắp xếp sản phẩm theo giá tiền Sale   |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("0.  |                 Thoát                   |");
            System.out.println("\t-------------------------------------------");
            System.out.print("\n\nNhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MeatManager.addNewMeat(scanner);
                    break;
                case 2:
                    System.out.println("\nDanh sách sản phẩm:");
                    MeatManager.displayMeats();
                    break;
                case 3:
                    System.out.print("Nhập mã sản phẩm cần tìm theo id: ");
                    String meatId = scanner.nextLine();
                    MeatManager.findMeat(meatId);
                    break;
                case 4:
                    MeatManager.displayMeats();
                    System.out.println("Nhập mã sản phẩm cần xóa: ");
                    String meatId1 = scanner.nextLine();
                    MeatManager.removeMeat(meatId1);
                    break;
                case 5:
                    MeatManager.displayMeats();
                    System.out.print("Nhập mã sản phẩm cần sửa: ");
                    String meatIdToEdit = scanner.nextLine();
                    MeatManager.editMeat(meatIdToEdit, scanner);
                    break;
                case 6:
                    MeatManager.sortMeatsByPrice();
                    break;
                case 7:
                    MeatManager.sortMeatsByExpirationDate();
                    break;
                case 8:
                    MeatManager.sortMeatsByMoney();
                case 0:
                    System.out.println("Quay lại Menu chính.");
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                    break;
            }
        } while (choice != 0);

    }
}
