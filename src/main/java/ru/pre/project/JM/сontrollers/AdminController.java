package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.models.User;
import ru.pre.project.JM.service.UserService;


import javax.validation.Valid;

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
    public String getAllUsers(Model model) {
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


}
