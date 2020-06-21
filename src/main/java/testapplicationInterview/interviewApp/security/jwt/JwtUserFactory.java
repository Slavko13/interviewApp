package testapplicationInterview.interviewApp.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import testapplicationInterview.interviewApp.domain.Roles;
import testapplicationInterview.interviewApp.domain.Status;
import testapplicationInterview.interviewApp.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                convertSimpleStringRoleToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> convertSimpleStringRoleToGrantedAuthorities(List<Roles> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
