package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.service.UserService;
import java.security.Principal;

@Controller
public class DefaultController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.loadUserModelByUsername(principal.getName()));
        return "index";
    }
}
