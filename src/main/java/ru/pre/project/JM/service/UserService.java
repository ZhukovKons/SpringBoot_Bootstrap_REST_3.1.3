package ru.pre.project.JM.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.pre.project.JM.models.Role;
import ru.pre.project.JM.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();

    User getUser(long id);

    void add(User user, List<String> role);

    void remove(long id);

    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    void addDefaultRoles();

    void updateUser(User user, List<String> role);

    List<Role> getAllRole();
}
