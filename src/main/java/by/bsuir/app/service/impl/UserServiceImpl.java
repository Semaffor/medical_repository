package by.bsuir.app.service.impl;

import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dto.UserDto;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.exception.UserAlreadyExistsException;
import by.bsuir.app.service.AbstractService;
import by.bsuir.app.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private final UserDao<User> userDao;

    public UserServiceImpl(UserDao<User> userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User registerNewUserAccount(UserDto userDto, PasswordEncoder passwordEncoder) throws UserAlreadyExistsException,
            ServiceException {
        if (findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("There is an account with the same login: "
                    + userDto.getUsername());
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(Set.of(Role.USER));

        user.setId(userDao.save(user));

        return user;
    }


}
