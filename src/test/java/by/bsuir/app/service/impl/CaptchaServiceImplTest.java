package by.bsuir.app.service.impl;

import by.bsuir.app.exception.InvalidReCaptchaException;
import by.bsuir.app.model.GoogleResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CaptchaServiceImpl captchaService;

    @Test(expected = InvalidReCaptchaException.class)
    public void testVerifyCaptchaShouldThrowExceptionWhenIncorrectResponse() {
        captchaService.verifyCaptcha("");
    }

    @Test(expected = InvalidReCaptchaException.class)
    public void testVerifyCaptchaShouldThrowExceptionWhenCaptchaNotValidated() {
        GoogleResponse response = Mockito.mock(GoogleResponse.class);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(new LinkedMultiValueMap<>(),
                new HttpHeaders());
//        Mockito.when(restTemplate.postForObject("", request, GoogleResponse.class)).thenReturn(response);
//        Mockito.when(response.isSuccess()).thenReturn(false);
        captchaService.verifyCaptcha("1234");

        Mockito.verify(restTemplate, Mockito.times(1)).postForEntity("", request, GoogleResponse.class);
    }
}