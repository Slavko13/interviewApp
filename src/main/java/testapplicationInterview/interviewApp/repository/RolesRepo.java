package testapplicationInterview.interviewApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import testapplicationInterview.interviewApp.domain.Roles;

@Repository
public interface RolesRepo extends CrudRepository<Roles, Long> {

    Roles findByRoleName(String roleName);
}
