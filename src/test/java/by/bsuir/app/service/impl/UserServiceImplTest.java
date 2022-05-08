package by.bsuir.app.service.impl;


import by.bsuir.app.dao.EmailValidationCodeDao;
import by.bsuir.app.dao.UserDao;
import by.bsuir.app.dto.CardDto;
import by.bsuir.app.dto.UserRegistrationDto;
import by.bsuir.app.entity.EmailValidationCode;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.DaoException;
import by.bsuir.app.exception.EmailNotFoundException;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.exception.UserAlreadyExistsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private EmailValidationCodeDao emailValidationCodeDao;

    @Mock
    private MailSender mailSender;

    private UserServiceImpl userService;
    private User user;

    @Before
    public void init() {
        userService = new UserServiceImpl(userDao, emailValidationCodeDao, mailSender);
        user = User.builder()
                .username("Dimonch")
                .password("12345")
                .email("dbu@gmail.com")
                .build();
    }

    @Test
    public void testSaveShouldPassWhenSuccess() {
        Mockito.when(userDao.save(user)).thenReturn(2L);
        userService.save(user);
        Mockito.verify(userDao, Mockito.times(1)).save(user);
    }

    @Test(expected = ServiceException.class)
    public void testSaveShouldThrowExceptionWhenError() {
        Mockito.when(userDao.save(user)).thenThrow(ServiceException.class);
        userService.save(user);
        Mockito.verify(userDao, Mockito.times(1)).save(user);
    }

    @Test
    public void testUpdateCardDtoShouldUpdateUserWhenUserExists() throws DaoException {
        Mockito.when(userDao.findById(2L)).thenReturn(Optional.of(user));
        CardDto cardDto = CardDto.fromUser(user, "ru");
        cardDto.setId(2L);

        Mockito.doNothing().when(userDao).update(any());
        CardDto result = userService.update(cardDto);
        Mockito.verify(userDao, Mockito.times(1)).findById(2L);
        Mockito.verify(userDao, Mockito.times(1)).update(any());

        Assert.assertEquals(cardDto, result);
    }

    @Test
    public void testUpdateCardDtoShouldNotUpdateUserWhenUserNotExists() throws DaoException {
        Mockito.when(userDao.findById(2L)).thenReturn(Optional.empty());
        CardDto cardDto = CardDto.fromUser(user, "ru");
        cardDto.setId(2L);
        userService.update(cardDto);

        Mockito.verify(userDao, Mockito.times(1)).findById(2L);
        Mockito.verify(userDao, Mockito.times(0)).update(any());
    }

    @Test
    public void testUpdateUserShouldPassWhenSuccess() {
        Mockito.doNothing().when(userDao).update(user);
        userService.update(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);
    }

    @Test
    public void testChangeBlockStatusByUsernameWhenUserExists() throws DaoException {
        Mockito.when(userDao.findByUsername("Dima")).thenReturn(Optional.of(user));

        boolean expected = !user.isBlocked();
        userService.changeBlockStatusByUsername("Dima");
        Mockito.verify(userDao, Mockito.times(1)).update(user);
        Assert.assertEquals(expected, user.isBlocked());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegisterNewUserAccountShouldThrowExceptionWhenUserLoginBusy() throws DaoException {
        Mockito.when(userDao.findByUsername("Dima")).thenReturn(Optional.of(user));

        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Dima");

        BCryptPasswordEncoder mockPE = Mockito.mock(BCryptPasswordEncoder.class);
        userService.registerNewUserAccount(dto, mockPE);

        Mockito.verify(userDao, Mockito.times(1)).findByUsername("Dima");
        Mockito.verify(userDao, Mockito.times(0)).findByEmail(anyString());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegisterNewUserAccountShouldThrowExceptionWhenEmailBusy() throws DaoException {
        Mockito.when(userDao.findByUsername("Dima")).thenReturn(Optional.empty());

        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Dima");
        dto.setEmail("some@some.by");

        Mockito.when(userDao.findByEmail("some@some.by")).thenReturn(Optional.of(user));
        BCryptPasswordEncoder mockPE = Mockito.mock(BCryptPasswordEncoder.class);
        userService.registerNewUserAccount(dto, mockPE);

        Mockito.verify(userDao, Mockito.times(1)).findByUsername("Dima");
        Mockito.verify(userDao, Mockito.times(1)).findByEmail(anyString());
    }

    @Test
    public void testRegisterNewUserAccountReturnRegisteredUserWhenUsernameAndEmailFree() throws DaoException {
        Mockito.when(userDao.findByUsername("Dima")).thenReturn(Optional.empty());

        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("Dimonch");
        dto.setPassword("12345");
        dto.setEmail("dbu@gmail.com");
        BCryptPasswordEncoder mockPE = Mockito.mock(BCryptPasswordEncoder.class);

        Mockito.when(userDao.findByEmail("some@some.by")).thenReturn(Optional.of(user));
        Mockito.when(emailValidationCodeDao.save(new EmailValidationCode(""))).thenReturn(2L);
        Mockito.when(mockPE.encode(anyString())).thenReturn("12345");
        Mockito.doNothing().when(mailSender).send(anyString(), anyString(), anyString());
        userService.registerNewUserAccount(dto, mockPE);

        Mockito.verify(userDao, Mockito.times(1)).findByUsername("Dimonch");
        Mockito.verify(emailValidationCodeDao, Mockito.times(1)).save(any());
        Mockito.verify(mailSender, Mockito.times(1)).send(anyString(), anyString(), anyString());
    }

    @Test
    public void textActivateUserShouldActivateWhenCodeExists() {
        user.setActivationCode(new EmailValidationCode());
        Mockito.when(userDao.findByActivationCode("")).thenReturn(Optional.of(user));
        Mockito.doNothing().when(emailValidationCodeDao).update(new EmailValidationCode());
        Mockito.doNothing().when(userDao).update(user);
        boolean isActivated = userService.activateUser("");

        Mockito.verify(userDao, Mockito.times(1)).findByActivationCode(any());
        Mockito.verify(emailValidationCodeDao, Mockito.times(1)).update(any());

        Assert.assertTrue(isActivated);
    }

    @Test
    public void textActivateUserShouldNotActivateWhenCodeNotExists() {
        Mockito.when(userDao.findByActivationCode("")).thenReturn(Optional.empty());
        Mockito.doNothing().when(emailValidationCodeDao).update(new EmailValidationCode());
        Mockito.doNothing().when(userDao).update(user);
        boolean isActivated = userService.activateUser("");
        Mockito.verify(emailValidationCodeDao, Mockito.times(0)).update(any());
        Assert.assertFalse(isActivated);
    }

    @Test
    public void testSendRecoveryLinkShouldSendMessageWhenEmailExists() throws DaoException {
        Mockito.when(userService.findByEmail("")).thenReturn(Optional.of(user));
        Mockito.when(emailValidationCodeDao.save(any())).thenReturn(2L);
        Mockito.doNothing().when(mailSender).send(anyString(), anyString(), anyString());

        userService.sendRecoveryLink("");

        Mockito.verify(userDao, Mockito.times(1)).findByEmail("");
        Mockito.verify(emailValidationCodeDao, Mockito.times(1)).save(any());
        Mockito.verify(mailSender, Mockito.times(1)).send(anyString(), anyString(), anyString());
    }

    @Test(expected = EmailNotFoundException.class)
    public void testSendRecoveryLinkShouldNotSendMessageWhenEmailNotExists() throws DaoException {
        Mockito.when(userService.findByEmail("")).thenReturn(Optional.empty());
        Mockito.when(emailValidationCodeDao.save(any())).thenReturn(2L);
        Mockito.doNothing().when(mailSender).send(anyString(), anyString(), anyString());

        userService.sendRecoveryLink("");

        Mockito.verify(userDao, Mockito.times(0)).findByEmail("");
        Mockito.verify(emailValidationCodeDao, Mockito.times(0)).save(any());
        Mockito.verify(mailSender, Mockito.times(0)).send(anyString(), anyString(), anyString());
    }

    @Test
    public void testChangePasswordReturnTrueWhenUserExists() throws DaoException {
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userDao).update(user);

        boolean result = userService.changePassword(1L, "newPassword", Mockito.mock(BCryptPasswordEncoder.class));

        Mockito.verify(userDao, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(userDao, Mockito.times(1)).update(user);

        Assert.assertTrue(result);
    }

    @Test
    public void testChangePasswordReturnFalseWhenUserNotExists() throws DaoException {
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        boolean result = userService.changePassword(1L, "newPassword", Mockito.mock(BCryptPasswordEncoder.class));

        Mockito.verify(userDao, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(userDao, Mockito.times(0)).update(user);

        Assert.assertFalse(result);
    }

    @Test
    public void testChangeRoleReturnTrueWhenUserExists() throws DaoException {
        Mockito.when(userService.findByUsername("")).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userDao).update(user);

        boolean result = userService.changeRole("", "USER");

        Mockito.verify(userDao, Mockito.times(1)).findByUsername("");
        Mockito.verify(userDao, Mockito.times(1)).update(user);

        Assert.assertTrue(result);
    }

    @Test
    public void testChangeRoleReturnFalseWhenUserNotExists() throws DaoException {
        Mockito.when(userService.findByUsername("")).thenReturn(Optional.empty());
        Mockito.doNothing().when(userDao).update(user);

        boolean result = userService.changeRole("", "USER");

        Mockito.verify(userDao, Mockito.times(1)).findByUsername("");
        Mockito.verify(userDao, Mockito.times(0)).update(user);

        Assert.assertFalse(result);
    }
}