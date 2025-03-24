package com.CMPDI.CPRMSE.NE.poratl.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.CMPDI.CPRMSE.NE.poratl.models.AppUser;
import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;
import com.CMPDI.CPRMSE.NE.poratl.services.AppUserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private AppUserService appUserService;

	
    @Autowired
    private PasswordEncoder passwordEncoder;
	 
    @GetMapping({"", "/"})
    public String home() {
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    
    
    @GetMapping("/values")
    public String details(HttpSession session, Model model) {
        String employeeCode = (String) session.getAttribute("employeeCode");
        
        if (employeeCode != null) {
            AppUser appUser = appUserService.findByEmployeecode(employeeCode);
            List<Transactions> transactions = appUserService.findTransactionsByEmployeeCode(employeeCode);
            model.addAttribute("appUser", appUser);
            model.addAttribute("transactions", transactions);
        }
        
        return "values";
    }
    
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "signup";
    }
    //for signup or changing password
    @PostMapping("/signup")
    public String signupForm(@ModelAttribute("appUser") AppUser newAppUser, Model model) {
        // Retrieve the existing AppUser from the database using employeeCode
        AppUser existingAppUser = appUserService.findByEmployeecode(newAppUser.getEmployeecode());

        if (existingAppUser != null) {
            // Update the fields provided by the user
            existingAppUser.setPassword(passwordEncoder.encode(newAppUser.getPassword()));

            // Save the updated AppUser to the database
            appUserService.save(existingAppUser);

            // Add a success message
            model.addAttribute("message", "Employee signup successfully!");
        } else {
            // Handle case when employeeCode does not exist
            model.addAttribute("errorMessage", "Employee code not found!");
        }

        return "signup";
    }

  
}
