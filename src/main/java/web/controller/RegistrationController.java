package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
class RegistrationController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String newUser() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(@RequestParam("name") String name, @RequestParam("password") String password,
                          @RequestParam("email") String email, @RequestParam("role") ArrayList<Long> role) {
        System.out.println("Ролииии - " + role.toString());
        User user = new User();
        user.setUsername(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(role.stream().map((r) -> userService.findByIdRole(r)).collect(Collectors.toSet()));
        userService.add(user);

        return "redirect:/login";
    }
}
