package by.bsuir.app.dao;

import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;

import java.util.Optional;

public interface UserDao<T> extends Dao<User>{
    Optional<T> findByUsername(String username) throws DaoException;
    Optional<T> findByEmail(String email) throws DaoException;
}
