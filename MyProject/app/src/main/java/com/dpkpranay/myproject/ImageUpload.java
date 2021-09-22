package com.dpkpranay.myproject;

public class ImageUpload {
    public String name;
    public String uri;
    public String getName(){
        return name;
    }
    public String getUri(){
        return uri;
    }

    public ImageUpload(String name,String uri) {
        this.name = name;
        this.uri=uri;
    }
    public ImageUpload(){}
}
