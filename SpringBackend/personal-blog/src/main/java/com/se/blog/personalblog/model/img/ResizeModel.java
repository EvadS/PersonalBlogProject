package com.se.blog.personalblog.model.img;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResizeModel {
    private int dx;
    private int dy;
    private int previewWidth;
    private int previewHeight;
    private int croppedWidth;
    private int croppedHeight;
}
