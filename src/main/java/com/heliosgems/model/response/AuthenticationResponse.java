package com.heliosgems.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AuthenticationResponse {
    private UUID uuid;
    private String role;
    private String description;
}
