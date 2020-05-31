package com.se.blog.personalblog.model.request;

import com.se.blog.personalblog.constraint.ContentType;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
public class UploadedImage {
    @Getter
    @Setter
    @ContentType({"JPG", "JPEG", "PNG", "BMP"})
    private MultipartFile file;
}
