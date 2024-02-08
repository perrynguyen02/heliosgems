package com.heliosgems.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    UUID uuid;
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    String phoneNumber;

}
