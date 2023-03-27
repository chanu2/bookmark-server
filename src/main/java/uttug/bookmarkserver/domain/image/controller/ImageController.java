package uttug.bookmarkserver.domain.image.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uttug.bookmarkserver.domain.image.dto.UploadImageResponse;
import uttug.bookmarkserver.domain.image.service.ImageService;

@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@RestController
public class ImageController {
     private final ImageService imageService;
    @PostMapping("/upload")
    public UploadImageResponse uploadImage(@RequestPart MultipartFile file) {
        return imageService.uploadImage(file);
    }


}