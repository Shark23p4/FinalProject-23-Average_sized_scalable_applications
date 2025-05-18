package com.wroteit.ModerationApp.service;

import com.wroteit.ModerationApp.command.*;
import com.wroteit.ModerationApp.model.Report;
import com.wroteit.ModerationApp.repository.ModeratorRepository;
import com.wroteit.ModerationApp.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ModeratorService {

    private final ReportRepository reportRepository;
    private final ModeratorRepository moderatorRepository;

    public ModeratorService(ReportRepository reportRepository, ModeratorRepository moderatorRepository) {
        this.reportRepository = reportRepository;
        this.moderatorRepository = moderatorRepository;
    }

    public Report fileReport(Report report) {
        report.setStatus("pending");
        report.setTimestamp(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public Report reviewReport(Long reportId, String newStatus) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));
        report.setStatus(newStatus);
        return reportRepository.save(report);
    }

    public List<Report> getReportsByStatus(String status) {
        return reportRepository.findByStatus(status);
    }

    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }

    public String assignModerator(Long userId, Long communityId) {
        ModerationCommand command = new AssignModeratorCommand(moderatorRepository, userId, communityId);
        command.execute();
        return "Moderator assigned.";
    }

    public String banUser(Long userId, Long communityId) {
        ModerationCommand command = new BanUserCommand(userId, communityId);
        command.execute();
        return "User banned.";
    }

    public String deleteContent(String entityType, Long entityId) {
        ModerationCommand command = new DeleteContentCommand(entityType, entityId);
        command.execute();
        return "Content deleted.";
    }

    @Transactional
    public String moderateReport(Long reportId, Long moderatorId, String action) {
        ModerationCommand command = new ModerateReportCommand(reportRepository, reportId, moderatorId, action);
        command.execute();
        return "Report moderated.";
    }
}
