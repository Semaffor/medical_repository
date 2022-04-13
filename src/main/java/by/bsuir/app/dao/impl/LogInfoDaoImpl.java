package by.bsuir.app.dao.impl;

import by.bsuir.app.dao.AbstractDao;
import by.bsuir.app.dao.LogInfoDao;
import by.bsuir.app.entity.LogInfo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class LogInfoDaoImpl extends AbstractDao<LogInfo> implements LogInfoDao {

    public LogInfoDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, LogInfo.class);
    }
}
