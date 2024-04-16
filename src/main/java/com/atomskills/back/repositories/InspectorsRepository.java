package com.atomskills.back.repositories;

import com.atomskills.back.models.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectorsRepository extends JpaRepository<Inspector, Integer> {
}
