package ru.pre.project.JM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pre.project.JM.entity.Role;
import ru.pre.project.JM.entity.RoleType;
import ru.pre.project.JM.entity.User;
import ru.pre.project.JM.model.UserModel;
import ru.pre.project.JM.repositorys.RoleRepository;
import ru.pre.project.JM.repositorys.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    @Transactional(readOnly = true)
    public List<UserModel> getAll() {
        List<User> listAllUser = userRepository.findAll();
        List<UserModel> listAll = listAllUser.stream().map(UserModel::getModel)
                .collect(Collectors.toList());
        return listAll;
    }

    @Override
    @Transactional(readOnly = true)
    public UserModel getUser(long id) {
        return UserModel.getModel(userRepository.findById(id).get());
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void add(UserModel model) {
        User user = model.getUser();
        user.setRoles(getAllRole().stream()
                .filter(x -> model.getRoles().contains(x.getRole().name()))
                .collect(Collectors.toSet()));
        userRepository.save(user);
    }

    @Override
    public UserModel updateUser(UserModel model) {
        User user = model.getUser();
        User oldUser = userRepository.findById(user.getId()).get();
        List<Role> listRole = getAllRole();
        if (user.getPassword().length() == 0) {
            user.setPassword(oldUser.getPassword());
        }
        if (model.getRoles() == null) {
            user.setRoles(oldUser.getRoles());
        } else {
            user.setRoles(listRole
                    .stream()
                    .filter(x -> model.getRoles().contains(x.getRole().name()))
                    .collect(Collectors.toSet()));
        }
        return UserModel.getModel(userRepository.save(user));
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
    @Transactional(readOnly = true)
    public UserModel loadUserModelByUsername(String s) throws UsernameNotFoundException {
        return UserModel.getModel((User) userRepository.findUserByEmail(s));
    }

    @Override
    public void addDefaultRoles() {
        roleRepository.save(new Role(RoleType.ADMIN));
        roleRepository.save(new Role(RoleType.USER));
    }
}
