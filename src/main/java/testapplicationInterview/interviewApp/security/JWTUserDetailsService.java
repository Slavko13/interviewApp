package testapplicationInterview.interviewApp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import testapplicationInterview.interviewApp.domain.User;
import testapplicationInterview.interviewApp.security.jwt.JwtUser;
import testapplicationInterview.interviewApp.security.jwt.JwtUserFactory;
import testapplicationInterview.interviewApp.service.UserService;


@Service
@Slf4j
public class JWTUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromBD = userService.findByUsername(username);

        if (userFromBD == null) {
            throw  new UsernameNotFoundException("User with username: " + username + " not founded");
        }

        JwtUser jwtUser = JwtUserFactory.create(userFromBD);
        log.info("INFO loadUserByUsername - user {} loaded", userFromBD.getUsername());
        return jwtUser;
    }
}
