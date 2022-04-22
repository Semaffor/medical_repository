package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        return findByCriteriaSingleResult(username, "username");
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return findByCriteriaSingleResult(email, "email");
    }
}
