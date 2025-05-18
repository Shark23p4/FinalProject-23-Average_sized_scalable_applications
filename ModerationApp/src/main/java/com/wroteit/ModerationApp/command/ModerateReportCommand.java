package com.wroteit.ModerationApp.command;

import com.wroteit.ModerationApp.model.Report;
import com.wroteit.ModerationApp.repository.ReportRepository;

public class ModerateReportCommand implements ModerationCommand {

    private final ReportRepository reportRepository;
    private final Long reportId;
    private final Long moderatorId;
    private final String action;

    public ModerateReportCommand(ReportRepository reportRepository, Long reportId, Long moderatorId, String action) {
        this.reportRepository = reportRepository;
        this.reportId = reportId;
        this.moderatorId = moderatorId;
        this.action = action;
    }

    @Override
    public void execute() {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));
        report.setStatus("resolved by moderator " + moderatorId + " via action: " + action);
        reportRepository.save(report);
    }
}
