package com.atomskills.back.repositories;

import com.atomskills.back.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<Report, Integer> {
}
