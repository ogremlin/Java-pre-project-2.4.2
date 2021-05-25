package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "/new_user")
	public String newUser(ModelMap model) {
		model.addAttribute("user", new User());
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
}