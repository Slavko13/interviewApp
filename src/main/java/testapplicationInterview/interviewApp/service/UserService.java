package testapplicationInterview.interviewApp.service;

import testapplicationInterview.interviewApp.domain.User;

import java.util.List;

public interface UserService {

    User signUpUser(User user);

    List<User> getAllUsers();

    User findById(Long id);

    Boolean deleteUser(Long id);

    User findByUsername(String username);

}
