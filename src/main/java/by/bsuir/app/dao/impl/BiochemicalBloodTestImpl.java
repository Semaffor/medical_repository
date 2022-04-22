package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.BiochemicalBloodTestDao;
import by.bsuir.app.entity.BiochemicalBloodTest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BiochemicalBloodTestImpl extends AbstractDao<BiochemicalBloodTest> implements BiochemicalBloodTestDao {

    public BiochemicalBloodTestImpl(SessionFactory sessionFactory) {
        super(sessionFactory, BiochemicalBloodTest.class);
    }
}
