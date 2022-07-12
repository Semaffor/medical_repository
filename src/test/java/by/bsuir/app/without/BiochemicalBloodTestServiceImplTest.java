//package by.bsuir.app.without;
//
//import by.bsuir.app.dto.BiochemicalBloodTestDto;
//import by.bsuir.app.entity.BiochemicalBloodTest;
//import by.bsuir.app.service.BiochemicalBloodTestService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class BiochemicalBloodTestServiceImplTest {
//
//    @Autowired
//    private BiochemicalBloodTestService service;
//
//    @Test
//    public void tt() {
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//        logger.error("erwrew");
//    }
//
//    @Test
//    public void testFindAllBioTestForDoctor() {
//        List<BiochemicalBloodTest> all = service.findAll();
//        assertThat(all).isNotEmpty();
//    }
//
//    @Test
//    @Rollback
//    public void testSaveUserBioTestResultShouldSave() {
//        BiochemicalBloodTestDto bloodTest = new BiochemicalBloodTestDto();
//        bloodTest.setUsername("Francis Morin");
//        bloodTest.setCholesterol(20);
//        BiochemicalBloodTest savedTest = service.save(bloodTest);
//        assertThat(savedTest.getId()).isNotNull();
//    }
//
//    @Test
//    @Rollback
//    public void testSaveUserBioTestResultShouldNotSave() {
//        BiochemicalBloodTestDto bloodTest = new BiochemicalBloodTestDto();
//        bloodTest.setUsername("IncorrectUsername");
//        BiochemicalBloodTest savedTest = service.save(bloodTest);
//        assertThat(savedTest.getId()).isNull();
//    }
//
//    @Test
//    @Rollback
//    public void testAddRecommendationToBioAnalyzesShouldAddNew() {
//        BiochemicalBloodTestDto bloodTest = new BiochemicalBloodTestDto();
//        bloodTest.setUsername("Francis Morin");
//        bloodTest.setCholesterol(20);
//        BiochemicalBloodTest savedTest = service.save(bloodTest);
//        boolean isSaved = service.addRecommendation("Francis Morin", savedTest.getId(), "Good");
//        assertThat(isSaved).isTrue();
//    }
//
//    @Test
//    public void testAddRecommendationToBioAnalyzesShouldNotAdd() {
//        boolean isSaved = service.addRecommendation("random", 20000L, "Good");
//        assertThat(isSaved).isFalse();
//    }
//}
