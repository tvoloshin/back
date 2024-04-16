package com.atomskills.back.repositories;

import com.atomskills.back.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUsersRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByLogin(String login);
}
