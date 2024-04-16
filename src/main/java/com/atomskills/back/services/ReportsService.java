package com.atomskills.back.services;

import com.atomskills.back.models.AppUser;
import com.atomskills.back.models.Inspector;
import com.atomskills.back.models.Report;
import com.atomskills.back.models.ReportStatus;
import com.atomskills.back.models.Scientist;
import com.atomskills.back.repositories.AppUsersRepository;
import com.atomskills.back.repositories.ReportsRepository;
import com.atomskills.back.repositories.ScientistsRepository;
import com.atomskills.back.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final AppUsersRepository appUsersRepository;
    private final ReportsRepository reportsRepository;
    private final ScientistsRepository scientistsRepository;

    @SneakyThrows
    public void upload(int userId, String title, MultipartFile file) {
        Scientist scientist = scientistsRepository.findById(userId).orElseThrow();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Report report = reportsRepository.save(
                Report.builder()
                    .title(title)
                    .scientist(scientist)
                    .filePath(fileName)
                    .status(ReportStatus.CREATED)
                    .updatedAt(new Date())
                    .build()
        );

        long size = file.getSize();
        FileUploadUtil.saveFile(String.valueOf(report.getId()), fileName, file);

//        FileUploadResponse response = new FileUploadResponse();
//        response.setFileName(fileName);
//        response.setSize(size);
//        response.setDownloadUri("/downloadFile/" + filecode);
    }

    public List<Report> findForUser(int id) {
        AppUser appUser = appUsersRepository.findById(id).orElseThrow();
        if (appUser instanceof Inspector) {
            return reportsRepository.findAll();
        }
        if (appUser instanceof Scientist) {
            return ((Scientist) appUser).getReports();
        }
        throw new IllegalStateException();
    }
}
