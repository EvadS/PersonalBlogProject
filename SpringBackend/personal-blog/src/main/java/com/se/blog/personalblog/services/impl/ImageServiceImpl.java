package com.se.blog.personalblog.services.impl;

import com.se.blog.personalblog.exception.IncorrectFileException;
import com.se.blog.personalblog.model.entity.Image;
import com.se.blog.personalblog.properties.FileStorageProperties;
import com.se.blog.personalblog.repositories.ImageRepository;
import com.se.blog.personalblog.services.ImageService;
import com.se.blog.personalblog.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final String PREVIEW_PREFIX = "preview_";

    private final ImageRepository imageRepository;

    private final StorageService storageService;

    private final FileStorageProperties fileStorageProperties;

    public ImageServiceImpl(ImageRepository imageRepository, StorageService storageService, FileStorageProperties fileStorageProperties) {
        this.imageRepository = imageRepository;
        this.storageService = storageService;
        this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public String storeFile(MultipartFile file) {
        // TODO: step 1 store one

        if (file.isEmpty()) {
            throw new IncorrectFileException("Incorrect file Size.");
        }
        UUID uuidName = UUID.randomUUID();
        String storedFilePath = storageService.storeFile(file, Optional.empty());

        Image image = Image.builder()
                .imageName(file.getOriginalFilename())
                .imagePath(storedFilePath)
                .build();

        imageRepository.save(image);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + fileStorageProperties.getImageResource() + "/")
                .path(storedFilePath)
                .toUriString();

        return fileDownloadUri;
    }

    @Override
    public String savePreviewImg(MultipartFile file) {
        //TODO: image Name ?
        String previewImageName = String.format("%s%s", PREVIEW_PREFIX, file.getOriginalFilename());
        String filePreviewName = storageService.storeFile(file, Optional.of(previewImageName));

        Path filePreviewPath = storageService.getFullPathByName(filePreviewName);

        int previewHeight = fileStorageProperties.getPreviewHeight();
        int previewWidth = fileStorageProperties.getPreviewWidth();

        // TODO: previewFilePath to file
        BufferedImage croppedBufferedImage = storageService.createPreview(filePreviewPath.toString(), previewHeight, previewWidth);
        String previewFilePath = storageService.storePreviewFile(croppedBufferedImage, filePreviewPath.toString());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + fileStorageProperties.getImageResource() + "/")
                .path(previewImageName)
                .toUriString();

        return fileDownloadUri;
    }

    private Dimension calculateAspectRatioFit(double srcWidth, double srcHeight, double maxWidth, double maxHeight) {
        double ratio = Math.min(maxWidth / srcWidth, maxHeight / srcHeight);

        double height = srcWidth * ratio;
        double width = srcHeight * ratio;

        Dimension dimension = new Dimension((int) width, (int) height);
        return dimension;
    }
}
