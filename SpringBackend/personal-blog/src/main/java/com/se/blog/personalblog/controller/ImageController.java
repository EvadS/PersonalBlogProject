package com.se.blog.personalblog.controller;

import com.se.blog.personalblog.constraint.ContentType;
import com.se.blog.personalblog.model.request.UploadedImage;
import com.se.blog.personalblog.model.response.ImagePreviewResponse;
import com.se.blog.personalblog.services.ImageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ApiOperation(value = "Upload files as a array. consumes MULTIPART_FORM_DATA_VALUE")
    @RequestMapping(path = "/upload", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> imageUpload(@Valid @ModelAttribute  UploadedImage uploadedImage)
    //        @RequestParam("file") @Valid)
    {

        // TODO: check file name
        // TODO: validate
        String filepath = imageService.storeFile(uploadedImage.getFile());


        return new ResponseEntity<>(filepath, HttpStatus.OK);
    }

    @ApiOperation(value = "Upload file. consumes MULTIPART_FORM_DATA_VALUE")
    @RequestMapping(path = "/upload-with-preview", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> imageUploadWithPreview(@RequestParam("file") MultipartFile file) {

        // TODO: check file name
        // TODO: validate file size
        String imagePath = imageService.storeFile(file);
        String previewFileName = imageService.savePreviewImg(file);
        ImagePreviewResponse imagePreviewResponse = ImagePreviewResponse.builder()
                .imagePreviewUrl(previewFileName)
                .imageUrl(imagePath)
                .build();

        return new ResponseEntity<>(imagePreviewResponse, HttpStatus.OK);
    }


}
