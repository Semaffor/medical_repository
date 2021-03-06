package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.GeneralBloodTestDao;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.pagination.Page;
import by.bsuir.app.pagination.Paged;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeneralBloodTestImpl extends AbstractDao<GeneralBloodTest> implements GeneralBloodTestDao {

    private final static String HQL_FIND_ALL = "select g FROM GeneralBloodTest g where g.user.username = :username";
    public GeneralBloodTestImpl(SessionFactory sessionFactory) {
        super(sessionFactory, GeneralBloodTest.class);
    }

    @Override
    public Page<GeneralBloodTest> findAllByUsername(int pageNumber, int size, String username) {
        List<GeneralBloodTest> tests = getCurrentSession()
                .createQuery(HQL_FIND_ALL, GeneralBloodTest.class)
                .setParameter("username", username)
                .setFirstResult(pageNumber*size)
                .setMaxResults(size)
                .getResultList();
        return new Page<>(tests);
    }

    @Override
    public int findAllCountByUsername(String username) {
        return getCurrentSession()
                .createQuery(HQL_FIND_ALL, GeneralBloodTest.class)
                .setParameter("username", username)
                .getResultList()
                .size();
    }
}
