package com.se.blog.personalblog.services.impl;

import com.se.blog.personalblog.model.PostTag;
import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.entity.Tag;
import com.se.blog.personalblog.model.response.PostResponse;
import com.se.blog.personalblog.repositories.PostRepository;
import com.se.blog.personalblog.repositories.TagRepository;
import com.se.blog.personalblog.services.ImageService;
import com.se.blog.personalblog.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final TagRepository tagRepository;

    private final PostRepository postRepository;

    private final ImageService imageService;

    public PostServiceImpl(TagRepository tagRepository, PostRepository postRepository, ImageService imageService) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
        this.imageService = imageService;
    }

    @Override
    public Page<Post> getPagePosts(Pageable pageable) {
        return null;
    }


    @Transactional
    @Override
    public Post createPost(PostTag postModel) {
        List<Tag> postTags = new ArrayList<>();

        for (Tag tag : postModel.getTags()) {
            Tag currentTag = tagRepository.getByName(tag.getName());
            if (currentTag != null) {
                tag.setId(currentTag.getId());
            } else {
                tagRepository.save(tag);
            }

            postTags.add(tag);
        }

        String previewFilePath = imageService.savePreviewImg(postModel.getPreviewMultipart());
        Post post = Post.builder()
                .previewText(postModel.getPost().getPreviewText())
                .content(postModel.getPost().getContent())
                .title(postModel.getPost().getTitle())
                .description(postModel.getPost().getDescription())
                .previewImgPath(previewFilePath)
                .publishingDate(postModel.getPost().getPublishingDate())
                //    .tags(new HashSet<>( postTags))
                .build();

        Post savedPost = postRepository.save(post);

     //   savedPost.getTags().add(postTags.get(0));
    //    savedPost = postRepository.save(post);

        return savedPost;
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
