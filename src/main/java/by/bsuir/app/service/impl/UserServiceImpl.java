package by.bsuir.app.service.impl;

import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dto.CardDto;
import by.bsuir.app.dto.UserRegistrationDto;
import by.bsuir.app.entity.User;
import by.bsuir.app.entity.UserCard;
import by.bsuir.app.entity.enums.Role;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.exception.UserAlreadyExistsException;
import by.bsuir.app.service.AbstractService;
import by.bsuir.app.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private final UserDao<User> userDao;

    public UserServiceImpl(UserDao<User> userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws ServiceException {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(CardDto cardDto) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findById(cardDto.getId());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setUsername(cardDto.getUsername());
                user.setEmail(cardDto.getEmail());
                UserCard userCard = user.getCard();
                if (userCard == null) {
                    userCard = new UserCard();
                    userCard.setUser(user);
                }
                userCard.setName(cardDto.getName());
                userCard.setSurname(cardDto.getSurname());
                userCard.setThirdName(cardDto.getThirdName());
                userCard.setBirthday(cardDto.getBirthday());
                userCard.setGender(cardDto.getGender());
                userCard.setMobile(cardDto.getMobile());

                user.setCard(userCard);
                userDao.update(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        userDao.update(user);
    }

    @Override
    public void changeBlockStatusByUsername(String username) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findByUsername(username);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                user.setBlocked(!user.isBlocked());
                userDao.update(user);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public User registerNewUserAccount(UserRegistrationDto userRegistrationDto, PasswordEncoder passwordEncoder) throws
            UserAlreadyExistsException,
            ServiceException {
        if (findByUsername(userRegistrationDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("There is an account with the same login: "
                    + userRegistrationDto.getUsername());
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setRoles(Set.of(Role.USER));

        user.setId(userDao.save(user));

        return user;
    }
}
