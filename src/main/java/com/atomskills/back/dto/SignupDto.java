package com.atomskills.back.dto;

import com.atomskills.back.models.UserRole;
import lombok.Data;

@Data
public class SignupDto {
    private String login;
    private UserRole userRole;
}
