package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.model.UserModel;
import ru.pre.project.JM.service.UserService;


import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@EnableWebSecurity
@RequestMapping(value = "/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("user", userService.loadUserModelByUsername(principal.getName()));
        model.addAttribute("allRoles", userService.getAllRole().stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toList()));
        model.addAttribute("userNew", new UserModel());
        model.addAttribute("userList", userService.getAll());
        return "/index";
    }

    @RequestMapping(value = "/update_{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("userAct") @Valid UserModel updateUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.hasErrors());
            return "redirect:/#edit_" + updateUser.getId();
        }
        userService.updateUser(updateUser);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete_{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") long id) {
        userService.remove(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userNew") @Valid UserModel userNew,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.hasErrors());
            return "redirect:/#new";
        }
        userService.add(userNew);
        return "redirect:/";
    }


}
