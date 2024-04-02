package storage;

import product.Food;

import java.util.List;

public interface IReadWriteFile {
    List<Food> readFile();
    void writeFile(List<Food> foods);
}
