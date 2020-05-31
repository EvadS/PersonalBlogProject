package com.se.blog.personalblog.model.request.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.se.blog.personalblog.constraint.ContentType;
import com.se.blog.personalblog.constraint.PublishingDateConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ApiModel(description = "All details for New Post Employee. ")
public class NewPostRequest {
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    @ApiModelProperty(notes = "Post description")
    private String description;

    @NotNull
    @Lob
    private String content;

    @NotNull
    private String previewText;

    @ContentType({"JPG", "JPEG", "PNG", "BMP"})
    private MultipartFile previewImage;

//    @PublishingDateConstraint
//    @JsonFormat(pattern="yyyy-mm-dd")
//    @ApiModelProperty(dataType = "java.util.Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publishingDate;


    private List<String> tags;

}
