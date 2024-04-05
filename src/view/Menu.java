package view;

import facade.FacadeFood;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        FacadeFood facade = new FacadeFood();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n\t------------------ Menu -------------------");
            System.out.println("1.  |              Quản lý Thịt               |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("2.  |              Quản lý Bánh               |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("3.  |          Hiển thị Thịt và Bánh          |");
            System.out.println("\t|-----------------------------------------|");
            System.out.println("0.  |                  Thoát                  |");
            System.out.println("\t-------------------------------------------");
            System.out.print("\n\nNhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MenuMeat.showManuMeat();
                    break;
                case 2:
                    MenuCookie.showManuCookie();
                    break;
                case 3:
                    facade.display();
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

