package com.se.blog.personalblog.services.impl;

import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.response.PostResponse;
import com.se.blog.personalblog.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public Page<Post> getPagePosts(Pageable pageable) {
        return null;
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        return null;
    }

    @Override
    public void delete(Long postId) {

    }

    @Override
    public PostResponse updateTitle(Long postid, String postTitle) {
        return null;
    }

    @Override
    public PostResponse updatePreview(Long id, String previewText) {
        return null;
    }

    @Override
    public PostResponse updatePreviewImage(int id, MultipartFile previewImage) {
        return null;
    }

    @Override
    public PostResponse updatePostContent(Long id, String content) {
        return null;
    }
}
