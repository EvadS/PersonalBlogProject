package com.se.blog.personalblog.services;

import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.entity.Tag;
import com.se.blog.personalblog.model.request.TagRequest;
import com.se.blog.personalblog.model.response.TagResponse;

import java.util.List;

public interface  TagService {
    List<Tag> getTopTags(int tagCount);

    Tag updateTag(Long tagId, TagRequest tagRequest);

    Tag createTag(TagRequest tagRequest);

    void delete(Long tagId);
}
