package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pre.project.JM.model.UserModel;
import ru.pre.project.JM.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping(value = "/user")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getUserPage(Model model, Principal principal) {
        model.addAttribute("user", userService.loadUserModelByUsername(principal.getName()));
        return "index";
    }
}
