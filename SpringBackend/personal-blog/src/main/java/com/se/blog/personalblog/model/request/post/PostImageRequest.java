package com.se.blog.personalblog.model.request.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class PostImageRequest {
    private int id;

    private MultipartFile previewImage;
}
