package com.atomskills.back.repositories;

import com.atomskills.back.models.Scientist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScientistsRepository extends JpaRepository<Scientist, Integer> {
}
