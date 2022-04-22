package by.bsuir.app.service;

import by.bsuir.app.dto.GeneralBloodTestDto;
import by.bsuir.app.exception.ServiceException;

public interface GeneralBloodTestService {
    void save(GeneralBloodTestDto test) throws ServiceException;
}
