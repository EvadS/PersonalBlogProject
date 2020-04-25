package com.se.blog.personalblog.services;

import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    Page<Post> getPagePosts(Pageable pageable);

    Post createPost(Post post);

    Post updatePost(Long postId, Post post);

    void delete(Long postId);

    PostResponse updateTitle(Long postid, String postTitle);

    PostResponse updatePreview(Long id, String previewText);

    PostResponse updatePreviewImage(int id, MultipartFile previewImage);

    PostResponse updatePostContent(Long id, String content);
}
