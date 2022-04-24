package by.bsuir.app.service;

import by.bsuir.app.dto.GeneralBloodTestDto;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.exception.ServiceException;

public interface GeneralBloodTestService {
    GeneralBloodTest save(GeneralBloodTestDto test) throws ServiceException;
}
