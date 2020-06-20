package testapplicationInterview.interviewApp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import testapplicationInterview.interviewApp.domain.Roles;
import testapplicationInterview.interviewApp.domain.Status;
import testapplicationInterview.interviewApp.domain.User;
import testapplicationInterview.interviewApp.repository.RolesRepo;
import testapplicationInterview.interviewApp.repository.UserRepo;
import testapplicationInterview.interviewApp.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RolesRepo rolesRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.rolesRepo = rolesRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUpUser(User user) {
        if (!isUsernameExist(user.getUsername())) {

            Roles role = rolesRepo.findByRoleName("ROLE_USER");
            List<Roles> rolesList = new ArrayList<>();
            rolesList.add(role);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(rolesList);
            user.setStatus(Status.NOT_ACTIVE);

            User registeredUser = userRepo.save(user);

            log.info("INFO signUpuUser - user: {} registered OK", registeredUser);
            return registeredUser;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepo.findAll();
        log.info("INFO getAll - {} users founded", users.size());
        return users;
    }

    @Override
    public User findById(Long id) {
        User userFromDB = userRepo.findById(id).orElse(null);
        if (userFromDB == null) {
            log.warn("INFO findById - no user founded by this id {}", id);
            return null;
        }
        log.info("INFO findById - this user founded - {}", userFromDB.getUsername());
        return userFromDB;
    }

    @Override
    public Boolean deleteUser(Long id) {
        User userFromDB = userRepo.findById(id).orElse(null);
        if (userFromDB == null) {
            log.warn("INFO deleteUser - {} with this id not founded", id );
            return false;
        }
        userRepo.delete(userFromDB);
        log.info("INFO deleteUser - {} user was deleted", userFromDB.getUsername() );
        return true;
    }

    @Override
    public User findByUsername(String username) {
        User userFromDB= userRepo.findByUsername(username);
        log.info("INFO findByUsername - user: {} founded by username {}", userFromDB, username);
        return userFromDB;
    }

    private Boolean isUsernameExist(String username) {
        User userFromDB = userRepo.findByUsername(username);
        if (userFromDB == null) {
            log.info("INFO isUsernameExist {} this username is free", username);
            return false;
        }
        log.info("INFO isUsernameExist {} this username is busy", username);
        return true;

    }
}
