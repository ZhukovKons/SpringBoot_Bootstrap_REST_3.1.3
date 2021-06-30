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
import java.util.stream.Collectors;


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
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/roles")
    public ResponseEntity getAllRoles(){
        return ResponseEntity.ok(userService.getAllRole().stream().map(role -> role.getRole()).collect(Collectors.toList()));
    }

   @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody UserModel updateUser, BindingResult bindingResult) {
        try {
            return ResponseEntity.ok(userService.updateUser(updateUser));
        }catch (Exception e){
         return ResponseEntity.badRequest().body("Error update user "+ updateUser.getName());
        }
    }

    @DeleteMapping(value = "/delete_{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        try {
            userService.remove(id);
            return ResponseEntity.ok("User id=" + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR: The user with this ID " + id + " could not be deleted! : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity createUser(
//            @ModelAttribute("userNew") @Valid UserModel userNew, todo
            @RequestBody UserModel userNew,
            BindingResult bindingResult) {

        try {
            userService.add(userNew);
            return ResponseEntity.ok("User " + userNew.getName() + " created");
        } catch (Exception uEx) {
            return ResponseEntity.badRequest().body("ERROR: User created: " + uEx.getMessage());
        }
//        if (bindingResult.hasErrors()) { todo
    }


}
