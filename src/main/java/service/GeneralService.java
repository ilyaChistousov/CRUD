package service;

import java.util.List;

public interface GeneralService <T, ID> {

    T getById(ID id);
    List<T> getAll();
    int update(T t);
    int delete(ID id);
}
