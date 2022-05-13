package by.bsuir.app.without;

import by.bsuir.app.exception.UserStatusException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void testAuthUserAsAdmin() {
        UserDetails admin = userDetailsService.loadUserByUsername("admin");
        assertThat(admin.getAuthorities().toString().equals("[ADMIN]")).isTrue();
    }

    @Test
    public void testAuthUserAsDoctor() {
        UserDetails admin = userDetailsService.loadUserByUsername("doc");
        assertThat(admin.getAuthorities().toString().equals("[DOCTOR]")).isTrue();
    }

    @Test
    public void testAuthUserAsSimpleUser() {
        UserDetails admin = userDetailsService.loadUserByUsername("2");
        assertThat(admin.getAuthorities().toString().equals("[USER]")).isTrue();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testAuthUserWithIncorrectUsername() {
       userDetailsService.loadUserByUsername("IncorrectUsername");
    }

    @Test(expected = UserStatusException.class)
    public void testAuthUserWithBlockedUserStatus() {
        userDetailsService.loadUserByUsername("Francis Morin");
    }
}