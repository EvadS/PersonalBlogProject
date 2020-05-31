package com.se.blog.personalblog.model.request.post;

import com.se.blog.personalblog.constraint.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class PostImageRequest {
    private int id;

    @ContentType("image/png")
    private MultipartFile previewImage;
}
