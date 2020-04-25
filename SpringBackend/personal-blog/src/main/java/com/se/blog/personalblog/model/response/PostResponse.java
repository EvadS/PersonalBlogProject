package com.se.blog.personalblog.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostResponse {
    private Long postId;
    private String postTitle;
    private Date postUpdate;
    private Date publishingDate;
}
