package com.se.blog.personalblog.controller;


import com.se.blog.personalblog.model.PostTag;
import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.entity.Tag;
import com.se.blog.personalblog.model.request.post.*;
import com.se.blog.personalblog.model.response.PostResponse;
import com.se.blog.personalblog.services.PostService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PostController {
    private final PostService postService;

    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(NewPostRequest.class, PostTag.class).setConverter(postDtoConverter);

    }

    Converter<NewPostRequest, PostTag> postDtoConverter = mappingContext -> {
        Post post = new Post();

        NewPostRequest postDto = mappingContext.getSource();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setPreviewText(postDto.getPreviewText());

        post.setPublishingDate(postDto.getPublishingDate());

        MultipartFile previewFile = postDto.getPreviewImage();

        Set<Tag> tags = postDto.getTags().stream()
                .map(tag -> Tag.builder().name(tag).build())
                .collect(Collectors.toSet());


        return new PostTag(post, tags,previewFile);
    };

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getPagePosts(pageable);
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@Valid @ModelAttribute NewPostRequest postRequest) {
        PostTag postModel = modelMapper.map(postRequest, PostTag.class);
        Post post = postService.createPost(postModel);
        PostResponse postResponse = modelMapper.map(post, PostResponse.class);
        return ResponseEntity.ok(post);
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


// TODO: move to helper
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


}
