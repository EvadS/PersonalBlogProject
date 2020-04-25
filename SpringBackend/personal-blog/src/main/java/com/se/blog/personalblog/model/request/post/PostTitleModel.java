package com.se.blog.personalblog.model.request.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostTitleModel {
    private Long id;
    private String title;
}
