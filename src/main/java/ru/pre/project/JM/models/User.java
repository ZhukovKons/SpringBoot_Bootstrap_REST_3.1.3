package ru.pre.project.JM.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
//    @NotEmpty(message = "Имя не может быть пустым") //todo
//    @Size(min = 2, max = 15, message = "Имя не может быть менее 2 символов или более 15")
    private String name;

    @Column(name = "lastname")
//    @NotEmpty(message = "Фамилия не может быть пустой")
//    @Size(min = 2, max = 15, message = "Фамилия не может быть менее 2 символов или более 15")
    private String lastname;

    @Column(name = "email", unique = true)
//    @Email(message = "Не верный формат Email")
    private String email;

    @Column
//    @Min(value = 18, message = "Для регистрации на сайте Вам должно быть не менее 18 лет")
//    @NotNull (message = "Возраст не может быть пустым")
    private Integer age;

//    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private String addRole;
    @Transient
    private String deleteRole;

    public User() {
    }

    public User(String name, String lastname, Integer age, String email, String password, Set<Role> roles) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddRole() {
        return addRole;
    }

    public void setAddRole(String addRole) {
        this.addRole = addRole;
    }

    public String getDeleteRole() {
        return deleteRole;
    }

    public void setDeleteRole(String deleteRole) {
        this.deleteRole = deleteRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean serchRoleAdmin() {
        return roles.stream().anyMatch(x -> x.getRole().equals(RoleType.ADMIN));
    }

    @Override
    public String toString() {
        String[] s = new String[1];
        if (roles != null) {
            roles.forEach(x -> s[0] = s[0] + x.getRole());
        } else {
            s[0] = null;
        }
        return "User: " +
                "id=" + id +
                ", name='" + name +
                ", lastname='" + lastname +
                ", email='" + email +
                ", password='" + password +
                ", roles=" + s[0];
    }
}