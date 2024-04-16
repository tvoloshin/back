package com.atomskills.back.services;

import com.atomskills.back.models.Inspector;
import com.atomskills.back.models.Scientist;
import com.atomskills.back.models.UserRole;
import com.atomskills.back.repositories.AppUsersRepository;
import com.atomskills.back.repositories.InspectorsRepository;
import com.atomskills.back.repositories.ScientistsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AppUsersRepository appUsersRepository;
    private final ScientistsRepository scientistsRepository;
    private final InspectorsRepository inspectorsRepository;

    public void signup(String login, UserRole userRole) {
        if (userRole.equals(UserRole.SCIENTIST)) {
            scientistsRepository.save(Scientist.builder().login(login).build());
            return;
        }
        if (userRole.equals(UserRole.INSPECTOR)) {
            inspectorsRepository.save(Inspector.builder().login(login).build());
            return;
        }
    }

    public int login(String login) {
        return appUsersRepository.findByLogin(login).orElseThrow().getId();
    }
}
