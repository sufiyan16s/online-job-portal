package com.labouriq.dao;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseOperations<T> {
    T create(T t) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
}

