package by.bsuir.app.service;

import by.bsuir.app.entity.User;
import by.bsuir.app.exception.ServiceException;

public interface LogInfoService {
    void addLogRecord(User user) throws ServiceException;

}
