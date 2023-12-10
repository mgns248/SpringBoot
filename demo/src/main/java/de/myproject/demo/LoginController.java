package de.myproject.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login", params = "!error")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login", params = "error=true")
    public String login(HttpServletRequest request, Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
