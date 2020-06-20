package testapplicationInterview.interviewApp.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Roles extends BaseEntity {

    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;


}
