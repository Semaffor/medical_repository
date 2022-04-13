package by.bsuir.app.security;

import by.bsuir.app.entity.User;
import by.bsuir.app.service.LogInfoService;
import by.bsuir.app.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final LogInfoService logInfoService;

    public UserDetailsServiceImpl(UserService userService, LogInfoService logInfoService) {
        this.userService = userService;
        this.logInfoService = logInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> optionalUser = userService.findByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                logInfoService.addLogRecord(user);
                userService.update(user);
                return SecurityUser.fromUser(user);
            }
            throw new UsernameNotFoundException("User doesn't exits.");
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
