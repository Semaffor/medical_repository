//package by.bsuir.app.dao.impl;
//
//import by.bsuir.app.dao.BiochemicalBloodTestDao;
//import by.bsuir.app.entity.BiochemicalBloodTest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//@ContextConfiguration(locations = "classpath:application-test.properties")
//@RunWith(SpringJUnit4ClassRunner.class)
//public class UserDaoImplTest {
//
//    @Autowired
//    private BiochemicalBloodTestDao biochemicalBloodTestDaoImpl;
//
//    @Test
//    public void findByUsername() {
//        BiochemicalBloodTest test = BiochemicalBloodTest.builder()
//                .cholesterol(20.0)
//                .glucose(22.0)
//                .protein(21.0)
//                .build();
//        biochemicalBloodTestDaoImpl.save(test);
//    }
//
//    @Test
//    public void findByEmail() {
//    }
//
//    @Test
//    public void findByActivationCode() {
//    }
//}