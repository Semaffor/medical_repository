package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.EmailValidationCodeDao;
import by.bsuir.app.entity.EmailValidationCode;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmailValidationCodeDaoImpl extends AbstractDao<EmailValidationCode> implements EmailValidationCodeDao{

    public EmailValidationCodeDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, EmailValidationCode.class);
    }
}
