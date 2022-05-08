package by.bsuir.app.service.impl;


import by.bsuir.app.dao.LogInfoDao;
import by.bsuir.app.entity.LogInfo;
import by.bsuir.app.service.LogInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LogInfoServiceImplTest {

    @Mock
    private LogInfoDao logInfoDao;

    @InjectMocks
    private LogInfoServiceImpl logInfoService;

    @Test
    public void testSaveShouldPassWhenArgNotNull() {
        LogInfo logInfo = new LogInfo();
        Mockito.when(logInfoDao.save(logInfo)).thenReturn(2L);
        logInfoService.save(logInfo);
        Mockito.verify(logInfoDao, Mockito.times(1)).save(logInfo);
    }
}