package com.se.blog.personalblog.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImagePreviewResponse {
    private String imageUrl;
    private String imagePreviewUrl;
}
