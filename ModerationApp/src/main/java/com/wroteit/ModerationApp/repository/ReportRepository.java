package com.wroteit.ModerationApp.repository;

import com.wroteit.ModerationApp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStatus(String status);
    List<Report> findByEntityTypeAndReportedEntityId(String entityType, Long reportedEntityId);
}
