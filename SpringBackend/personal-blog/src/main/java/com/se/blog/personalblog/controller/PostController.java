package com.se.blog.personalblog.controller;


import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.request.post.*;
import com.se.blog.personalblog.model.response.PostResponse;
import com.se.blog.personalblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getPagePosts(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody NewPostRequest postRequest) {
// TODO: use model mapper
        Post post = new Post();
        return postService.createPost(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody NewPostRequest postRequest) {

// TODO: use model mapper
        Post post = new Post();
        return postService.updatePost(postId, post);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/post/title")
    public ResponseEntity<?> changeTitle(@RequestBody @Valid PostTitleModel postTitle) {
        PostResponse postResponse = postService.updateTitle(postTitle.getId(),
                postTitle.getTitle());

        return new ResponseEntity<>(postResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/post/preview")
    public ResponseEntity<?> changePreview(@RequestBody @Valid PostPreviewRequest postPreview) {
        PostResponse postResponse = postService.updatePreview(postPreview.getId(),
                postPreview.getPreviewText());

        return new ResponseEntity<>(postResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/post/preview-image")
    public ResponseEntity<?> changePreviewImage(@RequestBody @Valid PostImageRequest postImageRequest) {
        PostResponse postResponse = postService.updatePreviewImage(postImageRequest.getId(),
                postImageRequest.getPreviewImage());

        return new ResponseEntity<>(postResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/post/content")
    public ResponseEntity<?> changePostContent(@RequestBody @Valid PostContentRequest postImageRequest) {
        PostResponse postResponse = postService.updatePostContent(postImageRequest.getId(),
                postImageRequest.getContent());

        return new ResponseEntity<>(postResponse, HttpStatus.ACCEPTED);
    }


}
