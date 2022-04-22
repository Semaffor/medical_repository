package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.GeneralBloodTestDao;
import by.bsuir.app.entity.GeneralBloodTest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralBloodTestImpl extends AbstractDao<GeneralBloodTest> implements GeneralBloodTestDao {

    public GeneralBloodTestImpl(SessionFactory sessionFactory) {
        super(sessionFactory, GeneralBloodTest.class);
    }
}
