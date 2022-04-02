package by.bsuir.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Serializable> implements Dao<T> {

    private Class<T> localClass;
    private final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public final void setLocalClass(Class<T> classToSet) {
        this.localClass = classToSet;
    }

    @Override
    @Transactional
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(getCurrentSession().get(localClass, id));
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + localClass.getName(), localClass).getResultList();
    }


    @Override
    @Transactional
    public void create(T entity) {
        getCurrentSession().save(entity);
    }


    @Override
    @Transactional
    public void update(T entity) {
        getCurrentSession().update(entity);
    }


    @Override
    @Transactional
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long entityId) {
        Optional<T> entity = findById(entityId);
        entity.ifPresent(this::delete);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}