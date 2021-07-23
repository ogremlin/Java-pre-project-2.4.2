package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/index")
    public String printUsers(ModelMap model) {
        model.addAttribute("messages", userService.listUsers());
        return "index";
    }

    @GetMapping("/new_user")
    public String newUser() {
        return "new_user";
    }

    @PostMapping(value = "/new_user")
    public String addUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                          @RequestParam("email") String email, ModelMap model) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.add(user);
        return "redirect:/index";
    }

    @GetMapping("/update_form/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update_user";
    }

    @PostMapping(value = "/update_user/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.updateUser(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.dropById(id);
        return "redirect:/index";
    }
}