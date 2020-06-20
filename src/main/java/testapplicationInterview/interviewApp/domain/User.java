package testapplicationInterview.interviewApp.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "app_user")
@Data
public class User extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    private String email;
    private String password;
    private String username;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "roles_id", referencedColumnName =  "id")}
                )
    private List<Roles> roles;


}
