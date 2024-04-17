package com.atomskills.back.dto;

import com.atomskills.back.models.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private int id;
    private UserRole userRole;
}
