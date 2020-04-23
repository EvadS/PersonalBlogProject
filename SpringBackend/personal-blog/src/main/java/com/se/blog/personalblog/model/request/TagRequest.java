package com.se.blog.personalblog.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "All details about the tag. ")
public class TagRequest {
    @ApiModelProperty(notes = "The tags name")
    private String tagName;
}
