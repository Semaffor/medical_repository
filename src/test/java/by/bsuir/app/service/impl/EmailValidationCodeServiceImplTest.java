package by.bsuir.app.service.impl;

import by.bsuir.app.dao.EmailValidationCodeDao;
import by.bsuir.app.entity.EmailValidationCode;
import by.bsuir.app.service.EmailValidationCodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidationCodeServiceImplTest {

    @Mock
    private EmailValidationCodeDao validationCodeDao;

    @InjectMocks
    private EmailValidationCodeServiceImpl emailValidationCodeService;

    @Test
    public void testFindByUniqueCodeReturnNotNullWhenExists() {
        EmailValidationCode mock = Mockito.mock(EmailValidationCode.class);
        Mockito.when(emailValidationCodeService.findByUniqueCode("")).thenReturn(Optional.of(mock));
        Optional<EmailValidationCode> result = emailValidationCodeService.findByUniqueCode("");
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testFindByUniqueCodeReturnNullWhenNotExists() {
        Mockito.when(emailValidationCodeService.findByUniqueCode("")).thenReturn(Optional.empty());
        Optional<EmailValidationCode> result = emailValidationCodeService.findByUniqueCode("");
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteCodeShouldPassAlways() {
        EmailValidationCode code = new EmailValidationCode();
        Mockito.doNothing().when(validationCodeDao).delete(code);
        emailValidationCodeService.delete(code);
        Mockito.verify(validationCodeDao, Mockito.times(1)).delete(Mockito.any());
    }
}