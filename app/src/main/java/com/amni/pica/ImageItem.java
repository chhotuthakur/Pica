package com.amni.pica;

public class ImageItem {
    private String imageUrl;

    public ImageItem() {
        // Default constructor required for Firebase
    }

    public ImageItem(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

