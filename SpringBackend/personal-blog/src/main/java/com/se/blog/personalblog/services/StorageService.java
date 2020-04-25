package com.se.blog.personalblog.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Optional;

public interface  StorageService {
     String storeFile(MultipartFile file, Optional<String> fileName );
    Resource loadFileAsResource(String fileName);
    boolean isFileValid(MultipartFile file);

    BufferedImage createPreview(String filePreviewPath, int previewHeight, int previewWidth);

    String storePreviewFile(BufferedImage croppedBufferedImage, String previewFilePath);

    Path getFullPathByName(String filePreviewName);

}
