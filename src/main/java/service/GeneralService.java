package service;

import repository.GeneralRepository;

import java.sql.SQLException;
import java.util.List;

public interface GeneralService <T, ID> {

    T getById(ID id);
    List<T> getAll();
    int update(T t, ID id);
    int delete(ID id);
}
