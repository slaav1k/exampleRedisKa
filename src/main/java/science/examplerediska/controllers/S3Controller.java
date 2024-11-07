package science.examplerediska.controllers;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import science.examplerediska.services.S3Service;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Получаем оригинальное имя файла
        String fileName = file.getOriginalFilename();
        // Вызываем метод загрузки файла
        String fileUrl = s3Service.uploadFile(fileName, file);
        return ResponseEntity.ok("File uploaded successfully: " + fileUrl);
    }


}
