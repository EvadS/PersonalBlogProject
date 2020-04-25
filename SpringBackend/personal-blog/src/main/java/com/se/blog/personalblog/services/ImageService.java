package com.se.blog.personalblog.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String storeFile(MultipartFile files);

    String savePreviewImg(MultipartFile file);
}
