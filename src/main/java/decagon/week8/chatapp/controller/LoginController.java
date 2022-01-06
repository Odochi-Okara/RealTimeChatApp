package decagon.week8.chatapp.controller;



import decagon.week8.chatapp.model.User;
import decagon.week8.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    UserService userService;


    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        model.addAttribute("newRegistration", null);
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpSession httpSession, User user, Model model) {
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        User user1 = userService.getUserByEmail(user.getEmail());
        if (user1 == null) {
            model.addAttribute("invalid", "User does not exist. Check login details or register.");
            return "login";
        }
        user1 = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (user1 == null) {
            model.addAttribute("invalid", "Incorrect password");
            return "redirect:/login";
        }
        httpSession.setAttribute("user", user1);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage(){
        return "index";
    }




    @GetMapping("/logout")
    public String loggingOut(Model model, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        return "redirect:/login";
    }
}

