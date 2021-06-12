package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.models.Role;
import ru.pre.project.JM.models.User;
import ru.pre.project.JM.service.UserService;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class DefaultController {

    private final UserService userService;

    @Autowired
    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping
    public String logOut() {
        return "redirect:/logout";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("userNew") User userNew) {
        return "new";
    }

    @PostMapping(value = "/new")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userNew") @Valid User userNew,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        userNew.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        userService.add(userNew);
        return "redirect:/admin";
    }

}
