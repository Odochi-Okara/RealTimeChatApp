package decagon.week8.chatapp.controller;


import decagon.week8.chatapp.model.User;
import decagon.week8.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    UserService userService;


    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String signUp(User user, Model model) {

        User userRegistered = userService.getUserByEmail(user.getEmail());
        if (userRegistered != null) {
            model.addAttribute("failed", "User with Email Already exist");

            return "register";
        }
        User savedUser = userService.addUser(user);
        System.out.println(savedUser);
        return "redirect:/";
    }
}

