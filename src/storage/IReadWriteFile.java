package storage;

import java.util.List;

public interface IReadWriteFile<T> {
    List<T> readFile();
    void writeFile(List<T> foods);
}
