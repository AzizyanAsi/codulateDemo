package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user", schema = "demo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "date_of_birth")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/dd/MM")
    private String dateOfBirth;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    //    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private UserPreferences userPreferences;

    public void addRole(Role role){
        roles.add(role);
    }

    public final boolean isProfileUpdated() {
        return firstName != null
                || lastName != null ||
                email != null ||
                phoneNumber != null ||
                username != null ||
                password != null ||
                dateOfBirth != null ||
                height != null ||
                weight != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
