package com.se.blog.personalblog.services.impl;

import com.se.blog.personalblog.exception.FileNotFoundException;
import com.se.blog.personalblog.exception.FileStorageException;
import com.se.blog.personalblog.model.img.ResizeModel;
import com.se.blog.personalblog.properties.FileStorageProperties;
import com.se.blog.personalblog.services.StorageService;
import com.se.blog.personalblog.utils.BufferedImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path fileStorageLocation;

    @Autowired
    public StorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    @Override
    public String storeFile(MultipartFile file, Optional<String> opFileName) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (opFileName.isPresent()) {
            fileName = opFileName.get();
        }

        // Normalize file name
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    @Override
    public boolean isFileValid(MultipartFile file) {
        return true;
    }

    @Override
    public BufferedImage createPreview(String filePreviewPath, int previewHeight, int previewWidth) {
        BufferedImage previewBufferedImage = null;
        try {
            previewBufferedImage = ImageIO.read(new File(filePreviewPath));

            int originalWidth = previewBufferedImage.getWidth();
            int originalHeight = previewBufferedImage.getHeight();

            ResizeModel resizeModel = BufferedImageUtils.buildResizeModelByCurrentSize(originalWidth,
                    originalHeight, previewWidth, previewHeight);

            BufferedImage croppedBufferedImage = BufferedImageUtils.cropImage(previewBufferedImage, resizeModel);
            return croppedBufferedImage;
        } catch (IOException e) {
            // TODO : set my exception
            e.printStackTrace();
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public String storePreviewFile(BufferedImage croppedBufferedImage, String previewFilePath) {
        try {
            File output = new File(previewFilePath);
            //TODO SE formatName
            ImageIO.write(croppedBufferedImage, "png", output);

            return previewFilePath;
        } catch (IOException e) {
            // TODO: correct exception
            e.printStackTrace();

            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public Path getFullPathByName(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }
}
