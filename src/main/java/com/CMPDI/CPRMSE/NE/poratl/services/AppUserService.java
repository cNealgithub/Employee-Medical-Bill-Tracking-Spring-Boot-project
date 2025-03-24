package com.CMPDI.CPRMSE.NE.poratl.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.CMPDI.CPRMSE.NE.poratl.models.AppUser;
import com.CMPDI.CPRMSE.NE.poratl.models.Transactions;
import com.CMPDI.CPRMSE.NE.poratl.repositories.AppUserRepository;
import com.CMPDI.CPRMSE.NE.poratl.repositories.TransactionsRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

 

    @Override
    public UserDetails loadUserByUsername(String employeecode) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmployeecode(employeecode);

        if (appUser != null) {
            var springUser = User.withUsername(appUser.getEmployeecode())
                                 .password(appUser.getPassword())  // This should be the encoded password
                                 .roles(appUser.getRole().split(","))
                                 .build();
            return springUser;
        }
        throw new UsernameNotFoundException("User not found with employeecode: " + employeecode);
    }

    public AppUser findByEmployeecode(String employeeCode) {
        return appUserRepository.findByEmployeecode(employeeCode);
    }

    public List<Transactions> findTransactionsByEmployeeCode(String employeecode) {
        return transactionsRepository.findTransactionsByEmployeecode(employeecode);
    }
    
    public void save(AppUser appUser) { 
    	appUserRepository.save(appUser); 
    	}

	
}

