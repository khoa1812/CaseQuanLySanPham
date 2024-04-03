package view;

import controller.CookieManager;

import java.util.Scanner;

public class MenuCookie {
    public static void showManuCookie() {
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
            System.out.println("0.  |                 Thoát                   |");
            System.out.println("\t-------------------------------------------");
            System.out.print("\n\nNhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CookieManager.addNewCookie(scanner);
                    break;
                case 2:
                    System.out.println("\nDanh sách sản phẩm:");
                    CookieManager.displayCookies();
                    break;
                case 3:
                    System.out.print("Nhập mã sản phẩm cần tìm theo id: ");
                    String cookieId = scanner.nextLine();
                    CookieManager.findCookie(cookieId);
                    break;
                case 4:
                    System.out.println("Nhập mã sản phẩm cần xóa: ");
                    String cookieId1 = scanner.nextLine();
                    CookieManager.removeCookie(cookieId1);
                    break;
                case 5:
                    System.out.print("Nhập mã sản phẩm cần sửa: ");
                    String cookieIdToEdit = scanner.nextLine();
                    CookieManager.editCookie(cookieIdToEdit, scanner);
                    break;
                case 6:
                    CookieManager.sortCookiesByPrice();
                    break;
                case 7:
                    CookieManager.sortCookiesByExpirationDate();
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}
