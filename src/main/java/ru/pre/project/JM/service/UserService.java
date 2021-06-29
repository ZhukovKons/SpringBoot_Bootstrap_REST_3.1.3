package ru.pre.project.JM.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.pre.project.JM.entity.Role;
import ru.pre.project.JM.entity.User;
import ru.pre.project.JM.model.UserModel;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserModel> getAll();

    UserModel getUser(long id);

    void add(UserModel model);

    void remove(long id);

    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    UserModel loadUserModelByUsername(String s);

    void addDefaultRoles();

    void updateUser(UserModel model);

    List<Role> getAllRole();
}
