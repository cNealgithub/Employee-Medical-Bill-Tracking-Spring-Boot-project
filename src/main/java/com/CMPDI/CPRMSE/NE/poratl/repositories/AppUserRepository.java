package com.CMPDI.CPRMSE.NE.poratl.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.CMPDI.CPRMSE.NE.poratl.models.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    public AppUser findByEmployeecode(String employeecode);  
    public @NonNull List<AppUser> findAll();
  
}
