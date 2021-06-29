package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.model.UserModel;
import ru.pre.project.JM.service.UserService;


import javax.validation.Valid;
import java.util.List;


@RestController
@EnableWebSecurity
@RequestMapping(value = "/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllUsers() {
        List<UserModel> users = userService.getAll();
        return ResponseEntity.ok(userService.getAll());
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
