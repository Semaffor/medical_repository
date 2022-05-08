package by.bsuir.app.service.impl;

import by.bsuir.app.dao.BiochemicalBloodTestDao;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dto.BiochemicalBloodTestDto;
import by.bsuir.app.dto.GeneralBloodTestDto;
import by.bsuir.app.entity.BiochemicalBloodTest;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BiochemicalBloodTestServiceImplTest {

    @Mock
    private BiochemicalBloodTestDao bioBloodTestDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private BiochemicalBloodTestServiceImpl bioBloodTestService;

    private User user;

    @Before
    public void init() {
        user = User.builder()
                .username("Dimonch")
                .password("12345")
                .email("dbu@gmail.com")
                .build();
    }

    @Test
    public void testSaveShouldSaveWhenUsernameExists() throws DaoException {
        Mockito.when(userDao.findByUsername("")).thenReturn(Optional.of(user));
        Mockito.when(bioBloodTestDao.save(Mockito.any())).thenReturn(2L);

        BiochemicalBloodTestDto test = new BiochemicalBloodTestDto();
        test.setCholesterol(20.0);
        test.setUsername("");

        BiochemicalBloodTest result = bioBloodTestService.save(test);
        Mockito.verify(userDao, Mockito.times(1)).findByUsername("");
        Mockito.verify(bioBloodTestDao, Mockito.times(1)).save(Mockito.any());
        Assert.assertEquals(test.getCholesterol(), result.getCholesterol(), 0.0001);
    }

    @Test
    public void testSaveShouldNotSaveWhenUsernameNotExists() throws DaoException {
        Mockito.when(userDao.findByUsername(null)).thenReturn(Optional.empty());

        bioBloodTestService.save(Mockito.mock(BiochemicalBloodTestDto.class));
        Mockito.verify(userDao, Mockito.times(1)).findByUsername(null);
        Mockito.verify(bioBloodTestDao, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testAddRecommendationShouldAddWhenUserAndTestExists() throws DaoException {
        BiochemicalBloodTest test = BiochemicalBloodTest.builder()
                .cholesterol(20.0)
                .build();
        Mockito.when(bioBloodTestDao.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(test));
        Mockito.when(userDao.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(user));
        Mockito.doNothing().when(bioBloodTestDao).update(test);

        boolean isRecommendationAdded = bioBloodTestService.addRecommendation(2L, 2L, "");
        Assert.assertTrue(isRecommendationAdded);
    }

    @Test
    public void testAddRecommendationShouldNotWhenTestNotExists() throws DaoException {
        Mockito.when(bioBloodTestDao.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        boolean isRecommendationAdded = bioBloodTestService.addRecommendation(2L, 2L, "");
        Assert.assertFalse(isRecommendationAdded);
    }
}