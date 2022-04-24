package by.bsuir.app.service;

import by.bsuir.app.dto.BiochemicalBloodTestDto;
import by.bsuir.app.entity.BiochemicalBloodTest;
import by.bsuir.app.exception.ServiceException;

public interface BiochemicalBloodTestService extends Service<BiochemicalBloodTest> {
    BiochemicalBloodTest save(BiochemicalBloodTestDto test) throws ServiceException;
}
