package com.se.blog.personalblog.model.request.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PostPreviewRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String previewText;
}
