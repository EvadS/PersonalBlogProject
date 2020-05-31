package com.se.blog.personalblog.model;

import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostTag {
    private Post post;
    private Set<Tag> tags;

    private MultipartFile previewMultipart;
}