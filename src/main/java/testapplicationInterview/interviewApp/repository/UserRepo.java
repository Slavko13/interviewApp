package testapplicationInterview.interviewApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import testapplicationInterview.interviewApp.domain.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String userName);
}
