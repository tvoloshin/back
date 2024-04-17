package com.atomskills.back.services;

import com.atomskills.back.dto.LoginResponse;
import com.atomskills.back.models.AppUser;
import com.atomskills.back.models.Inspector;
import com.atomskills.back.models.Scientist;
import com.atomskills.back.models.UserRole;
import com.atomskills.back.repositories.AppUsersRepository;
import com.atomskills.back.repositories.InspectorsRepository;
import com.atomskills.back.repositories.ScientistsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AppUsersRepository appUsersRepository;
    private final ScientistsRepository scientistsRepository;
    private final InspectorsRepository inspectorsRepository;

    public LoginResponse signup(String login, UserRole userRole) {
        if (userRole.equals(UserRole.SCIENTIST)) {
            Scientist scientist = scientistsRepository.save(Scientist.builder().login(login).build());
            return LoginResponse.builder().id(scientist.getId()).userRole(UserRole.SCIENTIST).build();
        }
        if (userRole.equals(UserRole.INSPECTOR)) {
            Inspector inspector = inspectorsRepository.save(Inspector.builder().login(login).build());
            return LoginResponse.builder().id(inspector.getId()).userRole(UserRole.INSPECTOR).build();
        }
        throw new IllegalStateException();
    }

    public LoginResponse login(String login) {
        AppUser appUser = appUsersRepository.findByLogin(login).orElseThrow();
        if (appUser instanceof Inspector) {
            return LoginResponse.builder().id(appUser.getId()).userRole(UserRole.INSPECTOR).build();
        }
        if (appUser instanceof Scientist) {
            return LoginResponse.builder().id(appUser.getId()).userRole(UserRole.SCIENTIST).build();
        }
        throw new IllegalStateException();
    }
}
