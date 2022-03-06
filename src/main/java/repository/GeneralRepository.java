package repository;

import java.util.List;

public interface GeneralRepository <T, ID> {

    T getById(ID id);
    List<T> getAll();
    int update(T t, ID id);
    int delete(ID id);
}
