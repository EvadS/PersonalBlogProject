package com.se.blog.personalblog.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@Configuration
@PropertySource(value = "classpath:media-config.properties")
public class FileStorageProperties {

    @Value("#{'${available.format}'.split(',')}")
    private List<String> imageFormats;

    @Value("#{'${storage.location}'}")
    private String uploadDir;

    @Value("#{'${storage.image.resource}'}")
    private String imageResource;

    @Value("#{'${preview.height}'}")
    private int previewHeight;

    @Value("#{'${preview.width}'}")
    private int previewWidth;
}