package by.bsuir.app.without;

import by.bsuir.app.controller.RegistrationController;
import by.bsuir.app.controller.admin.MessagesController;
import by.bsuir.app.controller.admin.ReportController;
import by.bsuir.app.controller.doctor.PatientsController;
import by.bsuir.app.controller.user.AnalyzesController;
import by.bsuir.app.controller.user.ConclusionsController;
import by.bsuir.app.controller.user.PersonalController;
import by.bsuir.app.controller.user.StatisticsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ContextControllerTesting {

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private ReportController reportController;

    @Autowired
    private MessagesController messagesController;

    @Autowired
    private AnalyzesController analyzesController;

    @Autowired
    private PatientsController patientsController;

    @Autowired
    private ConclusionsController conclusionsController;

    @Autowired
    private PersonalController personalController;

    @Autowired
    private StatisticsController statisticsController;


    @Test
    public void testRegistrationControllerShouldLoadContext () {
        assertThat(registrationController).isNotNull();
    }

    @Test
    public void testReportControllerShouldLoadContext () {
        assertThat(reportController).isNotNull();
    }

    @Test
    public void testMessagesControllerShouldLoadContext () {
        assertThat(messagesController).isNotNull();
    }

    @Test
    public void testAnalyzesControllerShouldLoadContext () {
        assertThat(analyzesController).isNotNull();
    }

    @Test
    public void testPatientsReportControllerShouldLoadContext () {
        assertThat(patientsController).isNotNull();
    }

    @Test
    public void testConclusionsControllerShouldLoadContext () {
        assertThat(conclusionsController).isNotNull();
    }

    @Test
    public void testStatisticsControllerShouldLoadContext () {
        assertThat(statisticsController).isNotNull();
    }

    @Test
    public void testPersonalControllerShouldLoadContext () {
        assertThat(personalController).isNotNull();
    }
}
