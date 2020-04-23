package com.se.blog.personalblog.services.impl;

import com.se.blog.personalblog.exception.ResourceNotFoundException;
import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.entity.Tag;
import com.se.blog.personalblog.model.request.TagRequest;
import com.se.blog.personalblog.repositories.TagRepository;
import com.se.blog.personalblog.services.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTopTags(int tagCount) {
        //TODO: add logic for sorting by related post
        Page<Tag> tagPage = tagRepository.findAll(PageRequest.of(0, tagCount));
        return tagPage.getContent();
    }

    @Override
    public Tag updateTag(Long tagId, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found for this id :: " + tagId));

        tag.setName(tagRequest.getTagName());
        tagRepository.save(tag);

        return tag;
    }

    @Override
    public Tag createTag(TagRequest tagRequest) {
        Optional<Tag> tag = tagRepository.findByName(tagRequest.getTagName());
        if (tag.isPresent())
            return tag.get();

        Tag newTag = new Tag();
        newTag.setName(tagRequest.getTagName());

        tagRepository.save(newTag);
        return newTag;
    }

    @Override
    public void delete(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found for this id :: " + tagId));


        // TODO: how about related post ?!
        tagRepository.delete(tag);

    }
}
