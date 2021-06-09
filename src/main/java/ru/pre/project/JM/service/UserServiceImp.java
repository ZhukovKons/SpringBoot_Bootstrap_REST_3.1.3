package ru.pre.project.JM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pre.project.JM.models.Role;
import ru.pre.project.JM.models.User;
import ru.pre.project.JM.repositorys.RoleRepository;
import ru.pre.project.JM.repositorys.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void add(User user) {
        System.out.println("ADD START: " + user.toString());
        userRepository.save(user);
    }

    @Override
    public void edit(User user, long id) { //todo
        user.setRoles(getUser(id).getRoles());
        //System.out.println(roleRepository.findRoleByRole(user.getAddRole()).getRole());
//        if (roleRepository.findRoleByRole(user.getAddRole()) != null) {
//            user.getRoles().add(roleRepository.findRoleByRole(user.getAddRole()));
//        }
//        if (roleRepository.findRoleByRole(user.getDeleteRole()) != null) {
//            user.getRoles().remove(roleRepository.findRoleByRole(user.getDeleteRole()));
//        }
        userRepository.save(user);
    }

    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(s);
    }

    @Override
    public void addDefaultRoles() { //todo
//        roleRepository.save(new Role(2l,"ROLE_ADMIN"));
//        roleRepository.save(new Role(2l, "ROLE_USER"));
    }
}
