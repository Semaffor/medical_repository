package by.bsuir.app.service.impl;

import by.bsuir.app.dao.GeneralBloodTestDao;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dto.GeneralBloodTestDto;
import by.bsuir.app.entity.GeneralBloodTest;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.pagination.Page;
import by.bsuir.app.pagination.Paged;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GeneralBloodTestServiceImplTest {

    @Mock
    private GeneralBloodTestDao generalBloodTestDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private GeneralBloodTestServiceImpl generalBloodTestService;

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
    public void testGetPageShouldReturnNotNullPageEntity() {
        GeneralBloodTest bloodTest = new GeneralBloodTest();
        Page<GeneralBloodTest> postPage = Mockito.mock(Page.class);
        Mockito.when(generalBloodTestDao.findAllByUsername(Mockito.anyInt(), Mockito.anyInt(),
                Mockito.anyString())).thenReturn(postPage);
        Mockito.when(generalBloodTestDao.findAllCountByUsername("")).thenReturn(10);
        Mockito.when(postPage.getObject()).thenReturn(new LinkedList<>() { { add(bloodTest);}});

        Paged<GeneralBloodTest> result = generalBloodTestService.getPage(1, 10, "", "ru");
        Assert.assertNotNull(result);
    }

    @Test
    public void testSaveShouldSaveWhenUsernameExists() throws DaoException {
        Mockito.when(userDao.findByUsername("")).thenReturn(Optional.of(user));
        Mockito.when(generalBloodTestDao.save(Mockito.any())).thenReturn(2L);

        GeneralBloodTestDto test = new GeneralBloodTestDto();
        test.setHemoglobinValue(20.0);
        test.setUsername("");

        GeneralBloodTest result = generalBloodTestService.save(test);
        Mockito.verify(userDao, Mockito.times(1)).findByUsername("");
        Mockito.verify(generalBloodTestDao, Mockito.times(1)).save(Mockito.any());
        Assert.assertEquals(test.getHemoglobinValue(), result.getHemoglobinValue(), 0.0001);
    }

    @Test
    public void testSaveShouldNotSaveWhenUsernameNotExists() throws DaoException {
        Mockito.when(userDao.findByUsername(null)).thenReturn(Optional.empty());

        generalBloodTestService.save(Mockito.mock(GeneralBloodTestDto.class));
        Mockito.verify(userDao, Mockito.times(1)).findByUsername(null);
        Mockito.verify(generalBloodTestDao, Mockito.times(0)).save(Mockito.any());
    }
}