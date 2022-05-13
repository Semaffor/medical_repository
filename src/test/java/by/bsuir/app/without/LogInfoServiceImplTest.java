package by.bsuir.app.without;

import by.bsuir.app.dto.LogInfoDto;
import by.bsuir.app.entity.LogInfo;
import by.bsuir.app.entity.User;
import by.bsuir.app.service.LogInfoService;
import by.bsuir.app.service.UserService;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LogInfoServiceImplTest {

    @Autowired
    private LogInfoService service;

    @Autowired
    private UserService userService;

    @Test
    @Rollback
    public void testCreateNewLogWithoutUser() {
        LogInfo newLog = new LogInfo();
        newLog.setTimestamp(new Date());
        service.save(new LogInfo());
        List<LogInfo> all = service.findAll();
        assertThat(all.contains(newLog)).isTrue();
    }

    @Test
    @Rollback
    public void testCreateNewLogWithExistsUser() {
        LogInfo newLog = new LogInfo();
        newLog.setTimestamp(new Date());
        User user = new User();
        user.setId(10L);
        newLog.setUser(user);
        service.save(new LogInfo());
        List<LogInfo> all = service.findAll();
        assertThat(all.contains(newLog)).isTrue();
    }

    @Test
    public void testGetAllStatistic() {
        List<LogInfo> all = service.findAll();
        assertThat(all).isNotNull();
    }

    @Test
    public void testGetAllStatisticCroupedByDays() {
        List<LogInfoDto> allGroupedByDays = service.findAllGroupedByDays();
        assertThat(allGroupedByDays).isNotNull();
    }
}
