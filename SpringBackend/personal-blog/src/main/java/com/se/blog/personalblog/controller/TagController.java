package com.se.blog.personalblog.controller;

import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.entity.Tag;
import com.se.blog.personalblog.model.request.TagRequest;
import com.se.blog.personalblog.model.response.TagResponse;
import com.se.blog.personalblog.services.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Api(value="Tags Management System", description="Operations pertaining to tag in Tags Management System")
@RestController("/tags")
public class TagController {

    private ModelMapper modelMapper;
    private  TagService tagService;

    Converter<Tag, TagResponse> tagTagResponseConverter = mappingContext -> {
        return new TagResponse(
                mappingContext.getSource().getId(),
                mappingContext.getSource().getName());
    };

    @Autowired
    public TagController(ModelMapper modelMapper, TagService tagService) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(Tag.class, TagResponse.class).setConverter(tagTagResponseConverter);
        this.tagService = tagService;
    }

    @ApiOperation(value = "View a list of  {top Count} available tags", response = List.class)
    @GetMapping("/tags")
    public List<TagResponse> getAllPosts(int tagCount) {
        List<Tag> tagList =  tagService.getTopTags(tagCount);

        List<TagResponse> convertedResult = tagList.stream()
                .map(x -> modelMapper.map(x, TagResponse.class))
                .collect(Collectors.toList());

        return convertedResult;
    }

    @ApiOperation(value = "Add a tag")
    @PostMapping("/tags")
    public ResponseEntity<TagResponse> createTag(
            @ApiParam(value = "Tag object store in database table", required = true) @Valid @RequestBody  TagRequest tagRequest) {
        Tag tag =  tagService.createTag(tagRequest);

        TagResponse tagResponse = modelMapper.map(tag, TagResponse.class);

        return new ResponseEntity<>(tagResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a tag")
    @PutMapping("/tags/{tagId}")
    public ResponseEntity<TagResponse> updatePost(
            @ApiParam(value = "Tag Id to update tag object", required = true) @PathVariable(value = "tagId") Long tagId,
            @ApiParam(value = "Update tag object", required = true) @Valid @RequestBody TagRequest tagRequest)  {
        Tag tag =  tagService.updateTag(tagId, tagRequest);;

        TagResponse tagResponse = modelMapper.map(tag, TagResponse.class);
        return new ResponseEntity<>(tagResponse, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Delete a tag")
    @DeleteMapping("/tags/{id}")
    public ResponseEntity<?> deletePost(
            @ApiParam(value = "Tags Id from which tag object will delete from database table", required = true)
            @PathVariable(value = "id") Long tagId) {
        tagService.delete(tagId);

        return ResponseEntity.ok().build();
    }

}
