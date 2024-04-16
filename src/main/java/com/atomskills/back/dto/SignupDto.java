package com.atomskills.back.dto;

import com.atomskills.back.models.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupDto {
    private String login;
    private UserRole userRole;
}
