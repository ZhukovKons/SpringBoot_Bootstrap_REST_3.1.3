package ru.pre.project.JM.model;

import ru.pre.project.JM.entity.Role;
import ru.pre.project.JM.entity.RoleType;
import ru.pre.project.JM.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserModel {

    private Long id;

    private String name;

    private String lastname;

    private String email;

    private Integer age;

    private String password;

    private List<String> roles = new ArrayList<>();

    public static UserModel getModel (User user){
        UserModel model = new UserModel();
        model.id = user.getId();
        model.name = user.getName();
        model.lastname = user.getLastname();
        model.email = user.getEmail();
        model.age = user.getAge();
        model.password = null; //user.getPassword();
        model.roles = user.getRoles().stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toList());
        return model;
    }

    public User getUser(){
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setLastname(this.lastname);
        user.setEmail(this.email);
        user.setAge(this.age);
        user.setPassword(this.password);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean serchRoleAdmin() {
        return roles.stream().anyMatch(x -> x.equals(RoleType.ADMIN.name()));
    }
}
