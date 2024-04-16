package com.atomskills.back.controllers;

import com.atomskills.back.models.Report;
import com.atomskills.back.services.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

}
