package by.bsuir.app.without;

import by.bsuir.app.dto.UserRegistrationDto;
import by.bsuir.app.entity.EmailValidationCode;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.EmailNotFoundException;
import by.bsuir.app.exception.UserAlreadyExistsException;
import by.bsuir.app.service.EmailValidationCodeService;
import by.bsuir.app.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Autowired
    private EmailValidationCodeService codeService;

    @Test
    public void testSearchingUserForAdminShouldFind() {
        Optional<User> doc = service.findByUsername("doc");
        assertThat(doc.isPresent()).isTrue();
    }

    @Test
    public void testSearchingUserForAdminShouldNotFindWhenNotExists() {
        Optional<User> doc = service.findByUsername("random");
        assertThat(doc.isPresent()).isFalse();
    }

    @Test
    public void testSearchingUserForAdminByEmailShouldFindWhenExists() {
        Optional<User> doc = service.findByEmail("dui.suspendisse@outlook.net");
        assertThat(doc.isPresent()).isTrue();
    }

    @Test
    public void testSearchingUserForAdminByEmailShouldNotFindWhenNotExists() {
        Optional<User> doc = service.findByEmail("incorrectMail");
        assertThat(doc.isPresent()).isFalse();
    }

    @Test
    public void testSearchingUserForAdminByUserIdShouldNotFindWhenNotExists() {
        Optional<User> doc = service.findById(30000L);
        assertThat(doc.isPresent()).isFalse();
    }

    @Test
    public void testSearchingUserForAdminByUserIdShouldFindWhenExists() {
        Optional<User> doc = service.findById(1L);
        assertThat(doc.isPresent()).isTrue();
    }

    @Test
    public void testActivateUserWhenCodeNotExists() {
        boolean isActivated = service.activateUser(UUID.randomUUID().toString());
        assertThat(isActivated).isFalse();
    }

    @Test
    public void testUserBlockUserWhenUserExists() {
        service.changeBlockStatusByUsername("Francis Morin");
        Optional<User> docAfter = service.findByUsername("Francis Morin");
        Assert.assertFalse(docAfter.get().isBlocked());
    }

    @Test
    public void testUserBlockUserWhenUserNotExists() {
        service.changeBlockStatusByUsername("NotExists");
        Optional<User> docAfter = service.findByUsername("NotExists");
        Assert.assertFalse(docAfter.isPresent());
    }

    @Test(expected = EmailNotFoundException.class)
    public void testSendRecoveryLinkOnEmailUserWhenEmailNotExists() {
        service.sendRecoveryLink("NotExists");
    }

    @Test
    public void testSendRecoveryLinkOnEmailUserWhenEmailExists() {
        service.sendRecoveryLink("dui.suspendisse@outlook.net");
    }

    @Test
    public void testChangeUserRoleWhenUsernameExists() {
        boolean isChanged = service.changeRole("Honorato Dunn", "ADMIN");
        assertThat(isChanged).isTrue();
    }

    @Test
    public void testChangeUserRoleWhenUsernameNotExists() {
        boolean isChanged = service.changeRole("NotExists", "ADMIN");
        assertThat(isChanged).isFalse();
    }

    @Test
    public void testChangePasswordWhenUserNotExists() {
        boolean isChanged = service.changePassword(0L, "newPass", new BCryptPasswordEncoder());
        assertThat(isChanged).isFalse();
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegisterNewUserWhenUsernameAlreadyExists() {
        UserRegistrationDto user = new UserRegistrationDto();
        user.setUsername("doc");
        user.setPassword("1234");
        user.setEmail("dd@list.ru");
        service.registerNewUserAccount(user, new BCryptPasswordEncoder());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegisterNewUserWhenEmailAlreadyExists() {
        UserRegistrationDto user = new UserRegistrationDto();
        user.setUsername("newUser");
        user.setPassword("1234");
        user.setEmail("nec@hotmail.org");
        service.registerNewUserAccount(user, new BCryptPasswordEncoder());
    }

    @Test
    @Rollback
    public void testRegisterNewUserWhenUserNotExists() {
        UserRegistrationDto user = new UserRegistrationDto();
        user.setUsername("newUser");
        user.setPassword("1234");
        user.setEmail("newEmail@hotmail.org");
        User registredUser = service.registerNewUserAccount(user, new BCryptPasswordEncoder());
        assertThat(registredUser.getId()).isNotNull();
    }
}
