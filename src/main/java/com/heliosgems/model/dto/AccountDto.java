package com.heliosgems.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    String phoneNumber;
    String role;
}
