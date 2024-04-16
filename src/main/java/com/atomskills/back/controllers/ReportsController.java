package com.atomskills.back.controllers;

import com.atomskills.back.dto.RejectDto;
import com.atomskills.back.models.Report;
import com.atomskills.back.services.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("{userId}/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile,
                                    @RequestParam("title") String title,
                                    @PathVariable("userId") int id) {
        reportsService.upload(id, title, multipartFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> find(@PathVariable("userId") int id) {
        List<Report> reports = reportsService.findForUser(id);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{reportId}/download")
    public ResponseEntity<?> download(@PathVariable("userId") int userId,
                                      @PathVariable("reportId") int reportId) {
        Resource resource = reportsService.download(userId, reportId);
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    @PutMapping("/{reportId}/approve")
    public ResponseEntity<?> approve(@PathVariable("userId") int userId,
                                     @PathVariable("reportId") int reportId) {
        boolean result = reportsService.approve(userId, reportId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{reportId}/reject")
    public ResponseEntity<?> reject(@PathVariable("userId") int userId,
                                    @PathVariable("reportId") int reportId,
                                    @RequestBody RejectDto dto) {
        boolean result = reportsService.reject(userId, reportId, dto.getMessage());
        return ResponseEntity.ok(result);
    }

}
