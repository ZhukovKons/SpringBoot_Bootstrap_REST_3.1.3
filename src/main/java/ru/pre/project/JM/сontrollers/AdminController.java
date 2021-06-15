package ru.pre.project.JM.сontrollers;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.models.Role;
import ru.pre.project.JM.models.RoleType;
import ru.pre.project.JM.models.User;
import ru.pre.project.JM.service.UserService;


import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
@EnableWebSecurity
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("user", (User) userService.loadUserByUsername(principal.getName()));
        model.addAttribute("allRoles",userService.getAllRole());
        model.addAttribute("userNew", new User());
        List <Object> testList = new ArrayList<>(); //todo
        model.addAttribute("testList", testList);
        model.addAttribute("userList", userService.getAll());
        return "/admin";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/editor";
    }

    @RequestMapping(value = "//update_{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "/editor";
        }
        userService.edit(user, id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete_{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") long id) {
        userService.remove(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userNew") @Valid User userNew,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
       // System.out.println(userNew.getRoles());
        //System.out.println(userNew.getAddRole());
        //userNew.setRoles(Collections.singleton(new Role(2L, RoleType.USER))); //todo

        //userService.add(userNew);
        return "redirect:/admin";
    }


}
