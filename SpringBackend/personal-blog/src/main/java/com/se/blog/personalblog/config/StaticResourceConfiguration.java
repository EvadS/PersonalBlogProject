package com.se.blog.personalblog.config;

import com.se.blog.personalblog.properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    private String mediaRootLocation;
    private int cashPeriod = 36;

    private final Path fileStorageLocation;
    String imagesResources;

    @Autowired
    public StaticResourceConfiguration(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        this.mediaRootLocation = fileStorageProperties.getUploadDir();
        this.imagesResources = fileStorageProperties.getImageResource();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

int a =10;
        registry.addResourceHandler("/" +imagesResources +"/**")
                .addResourceLocations("file:" + mediaRootLocation + "/")
                .setCachePeriod(cashPeriod);
    }
}
