package com.CMPDI.CPRMSE.NE.poratl.controller;


import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.CMPDI.CPRMSE.NE.poratl.Response.ResponseMessage;
import com.CMPDI.CPRMSE.NE.poratl.models.AppUser;
import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;
import com.CMPDI.CPRMSE.NE.poratl.services.AppUserService;
import com.CMPDI.CPRMSE.NE.poratl.services.Fileservice;
import com.CMPDI.CPRMSE.NE.poratl.services.TransactionService;


import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	 @Autowired
	    private AppUserService appUserService;

		
		  @Autowired 
		    private Fileservice service;
		  
		  @Autowired
		    private TransactionService transactionService;
		  
		  @Autowired
		    private PasswordEncoder passwordEncoder;
		  
		  
		  @GetMapping("/admin")
		    public String adminPage(HttpSession session, Model model) {
		        String employeeCode = (String) session.getAttribute("employeeCode");

		        if (employeeCode != null) {
		            AppUser appUser = appUserService.findByEmployeecode(employeeCode);
		            model.addAttribute("appUser", appUser);
		        }

		        return "admin";
		    }
		    @GetMapping("/admin/crudutil")
		    public String adcrudutil() {
		    	return "crudutil";
		    }
		    
		    @GetMapping("/admin/crudutil/search")
		    public String searchAppUser(@RequestParam("employeecode") String employeecode, Model model) {
		        AppUser appUser = appUserService.findByEmployeecode(employeecode);
		        model.addAttribute("appUser", appUser);
		        return "crudutil";
		    }
		    
		    @PostMapping("/admin/crudutil/update")
		    public String updateAppUser(@ModelAttribute AppUser appUser, Model model, Principal principal) {
		        AppUser existingUser = appUserService.findByEmployeecode(appUser.getEmployeecode());
				/* formatting date type to dd-MM-yyy */
		        LocalDate dt = LocalDate.now();
		        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        String myDate = dt.format(df);
		        /* formatting date type to dd-MM-yyy */
		        // Preserve other fields and only update the necessary ones
		        if (appUser.getName() != null) {
		            existingUser.setName(appUser.getName());
		        }
		        if (appUser.getAadhar() != null) {
		            existingUser.setAadhar(appUser.getAadhar());
		        }
		        if (appUser.getDob() != null) {
		            existingUser.setDob(appUser.getDob());
		        }
		        if (appUser.getLocation() != null) {
		            existingUser.setLocation(appUser.getLocation());
		        }
		        if (appUser.getMedical_card_no() != null) {
		            existingUser.setMedical_card_no(appUser.getMedical_card_no());
		        }
		        if (appUser.getRole() != null) {
		            existingUser.setRole(appUser.getRole());
		        }

		        // Update edited fields
		        if (appUser.getClaiment_name() != null) {
		            existingUser.setClaiment_name(appUser.getClaiment_name());
		        }
		        if (appUser.getClaiment_aadhar() != null) {
		            existingUser.setClaiment_aadhar(appUser.getClaiment_aadhar());
		        }

		        // Automatically set modified_by and modified_onn
		        existingUser.setModified_by(principal.getName()); // Assuming the username is stored in the Principal object
		        existingUser.setModified_onn(myDate);
		        System.out.println(existingUser.getModified_onn());

		        appUserService.save(existingUser);

		        // Debugging statement after saving
		        AppUser savedUser = appUserService.findByEmployeecode(appUser.getEmployeecode());
		        System.out.println("After Save - Modified Onn: " + savedUser.getModified_onn());

		        model.addAttribute("appUser", existingUser);
		        model.addAttribute("message", "Update successful!");
		        return "crudutil";
		    }

		    
		    @GetMapping("/admin/excelutil/transact")
		    public String adexcelutiltransact() {
		    	return "upload";
		    }
			
			  @PostMapping("/admin/excelutil/transact") public
			  ResponseEntity<ResponseMessage> uploadfile(@RequestParam("file")
			  MultipartFile file) { if (service.hasCsvFormat(file)) {
			  service.ProcessAndSaveData(file); return ResponseEntity.status(HttpStatus.OK)
			  .body(new ResponseMessage("File Uploaded Successfully" +
			  file.getOriginalFilename())); } return
			  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
			  ResponseMessage("please upload csv file")); }
			
			  
		

			  @GetMapping("/searchTransactions")
			  public String searchTransactions(@RequestParam("employeecode") String employeecode,
			                                   @RequestParam(value = "month", required = false) String month,
			                                   @RequestParam(value = "year", required = false) String year,
			                                   Model model) {
			      List<Transactions> transactions = transactionService.getTransactions(employeecode, month, year);
			      model.addAttribute("transactions", transactions);
			      return "admin";
			  }

			

			      @GetMapping("/admin/register")
			      public String showRegistrationForm(Model model) {
			          model.addAttribute("appUser", new AppUser());
			          return "addemp";
			      }

			      @PostMapping("/admin/register")
			      public String registerAppUser(@ModelAttribute AppUser appUser, Model model) {
			    	  //setting the role as USER bydefault
			    	  appUser.setRole("USER");
			    	// Encode the password before saving the user
			          appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
			          // Save the appUser to the database (or process it accordingly)
			    	  appUserService.save(appUser);

			          // Add a success messag
			          model.addAttribute("message", "Employee registered successfully!");

			          return "addemp";
			      }
			  }


