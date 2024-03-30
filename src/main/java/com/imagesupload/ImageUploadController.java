package com.imagesupload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/upload-image")
public class ImageUploadController {
    private final ImageUploadService imageService;

    public ImageUploadController(ImageUploadService imageService) {this.imageService = imageService;}

    @PostMapping("/upload")
    public ResponseEntity<List<ImageDetails>> upload(@RequestParam("image") List<MultipartFile> images) {
        return new ResponseEntity<>(imageService.uploadImage(images), HttpStatus.OK);
    }
}
