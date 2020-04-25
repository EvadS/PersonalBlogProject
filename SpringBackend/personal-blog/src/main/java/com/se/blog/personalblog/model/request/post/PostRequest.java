package com.se.blog.personalblog.model.request.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String description;
    private  String content;

}
