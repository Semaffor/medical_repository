package by.bsuir.app.service;

import by.bsuir.app.dto.UserDto;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id) throws ServiceException;
    Optional<User> findByUsername(String username) throws ServiceException;
    Optional<User> findByEmail(String email) throws ServiceException;
    User registerNewUserAccount(UserDto userDto, PasswordEncoder passwordEncoder) throws UserAlreadyExistsException,
            ServiceException;
}
