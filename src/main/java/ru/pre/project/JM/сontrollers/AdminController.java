package ru.pre.project.JM.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pre.project.JM.entity.RoleType;
import ru.pre.project.JM.model.UserModel;
import ru.pre.project.JM.service.UserService;

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

    @GetMapping(value = "/json")
    public ResponseEntity getAllUsers(@RequestHeader ("type") String type) {
        try {
            if(type.equals("all")){
                return ResponseEntity.ok(userService.getAll());
            }
            return ResponseEntity.ok(userService.getUser(Long.parseLong(type)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("ERROR: Не удалось получить пользователя/ей ::: " + e.getMessage());
        }
    }

    @GetMapping(value = "/roles")
    public ResponseEntity getAllRoles(){
        return ResponseEntity.ok(userService.getAllRole().stream().map(role -> role.getRole()).collect(Collectors.toList()));
    }

   @PutMapping(value = "/add")
    public ResponseEntity update(@RequestBody UserModel updateUser) {
        try {
            return ResponseEntity.ok(userService.updateUser(updateUser));
        }catch (Exception e){
         return ResponseEntity.badRequest().body("ERROR update user "+ updateUser.getName());
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity delete(@RequestHeader ("id") long id) {
        try {
            userService.remove(id);
            return ResponseEntity.ok("User id=" + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR: The user with this ID " + id + " could not be deleted! : " + e.getMessage());
        }
    }

   @PostMapping(value = "/add")
    public ResponseEntity createUser(@RequestBody UserModel userNew) {

        try {
            userService.add(userNew);
            return ResponseEntity.ok("User " + userNew.getName() + " created");
        } catch (Exception uEx) {
            return ResponseEntity.badRequest().body("ERROR: Didn't create user " + userNew.getName());
        }
    }


}
