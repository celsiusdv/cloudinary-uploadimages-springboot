package com.imagesupload;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageUploadService {

    @Resource
    private Cloudinary cloudinary;

    private final ImageRepository imageRepository;

    public ImageUploadService(ImageRepository imageRepository) {this.imageRepository = imageRepository;}

    public String uploadFile(MultipartFile file, String folderName) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map<?,?> uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<ImageDetails> uploadImage(List<MultipartFile> files) {
        try {
            List<ImageEntity> images = new ArrayList<>();
            for(MultipartFile file:files){
                ImageEntity image = new ImageEntity();
                image.setName(file.getOriginalFilename());
                image.setUrl(this.uploadFile(file, "products"));
                images.add(image);
            }
            return imageRepository.saveAll(images).stream()
                    .map(ImageDetails::new)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
