package com.heliosgems.services;

import com.heliosgems.model.dto.AccountDto;
import com.heliosgems.model.entity.Account;
import com.heliosgems.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class AuthService {
   @Autowired
   private AccountRepo accountRepo;
   @GetMapping()
   public List<Account> getAllAccount() {
      return accountRepo.findAll();
   }

   @PostMapping("/signup")
   public String signupAccount(AccountDto accountDto) {
      return null;
   }

   @PostMapping("/signin")
   public String signinAccount(AccountDto accountDto) {
      return null;
   }

   @PostMapping("/signout")
   public void signoutAccount() {

   }
}
