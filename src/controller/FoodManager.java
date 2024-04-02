package controller;

import product.Food;
import storage.IReadWriteFile;
import storage.ReadWriteFile;

import java.util.List;

public class FoodManager {
    private static IReadWriteFile readWriteFile = new ReadWriteFile();
    private static List<Food> foods = readWriteFile.readFile();

    public static void addNewFood(Food food) {
        foods.add(food);
        readWriteFile.writeFile(foods);
        System.out.println("Sản phẩm đã được thêm thành công.");
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

    public static void editFood(String foodId, Food updatedFood) {
        int foodIndex = -1;
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getId().equals(foodId)) {
                foodIndex = i;
                break;
            }
        }
        if (foodIndex != -1) {

            foods.set(foodIndex, updatedFood);
            readWriteFile.writeFile(foods);
            System.out.println("Sản phẩm đã được cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + foodId);
        }
    }
}
