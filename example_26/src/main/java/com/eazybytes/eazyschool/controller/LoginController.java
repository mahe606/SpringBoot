package com.eazybytes.eazyschool.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                    @RequestParam(value = "logout", required = false) String logout,
                                    @RequestParam(value = "register", required = false) String register,
                                   Model model){

        String errorMessage = null;
        if(error != null)
            errorMessage = "UserName or Password is incorrect";
        else if(logout != null)
            errorMessage = "You have been successfully logged out !!";
        else if(register != null)
            errorMessage = "Your Registration is Successful.. Login with registered Credentials!!";

        model.addAttribute("errorMessage", errorMessage);

        return "login.html";

    }

    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);

        return "redirect:/login?logout=true";
    }
}