package by.bsuir.app.service.impl;

import by.bsuir.app.dao.BiochemicalBloodTestDao;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dto.BiochemicalBloodTestDto;
import by.bsuir.app.entity.BiochemicalBloodTest;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.AbstractService;
import by.bsuir.app.service.BiochemicalBloodTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class BiochemicalBloodTestServiceImpl extends AbstractService<BiochemicalBloodTest> implements BiochemicalBloodTestService {

    private final UserDao userDao;
    private final BiochemicalBloodTestDao bloodTestDao;

    public BiochemicalBloodTestServiceImpl(UserDao userDao, BiochemicalBloodTestDao bloodTestDao) {
        super(bloodTestDao);
        this.userDao = userDao;
        this.bloodTestDao = bloodTestDao;
    }

    @Override
    @Transactional
    public void save(BiochemicalBloodTestDto testDto) throws ServiceException {
        BiochemicalBloodTest test = new BiochemicalBloodTest();
        try {
            Optional<User> userOptional = userDao.findByUsername(testDto.getUsername());
            if (userOptional.isPresent()) {
                test.setCholesterol(testDto.getCholesterol());
                test.setGlucose(testDto.getGlucose());
                test.setProtein(testDto.getProtein());
                test.setUser(userOptional.get());
                test.setCreatedOn(new Date());
                bloodTestDao.save(test);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
