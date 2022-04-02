package by.bsuir.app.service;

import by.bsuir.app.entity.User;
import by.bsuir.app.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id) throws ServiceException;
    Optional<User> findByUsername(String username) throws ServiceException;
}
