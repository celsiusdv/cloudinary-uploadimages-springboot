package com.imagesupload;

public class ImageDetails {
    private Integer id;
    private String name;
    private String url;
    public ImageDetails(){}
    public ImageDetails(ImageEntity imageEntity){
        this.id=imageEntity.getId();
        this.name=imageEntity.getName();
        this.url=imageEntity.getUrl();
    }

    public Integer getId() {return id;}
    public String getName() {return name;}
    public String getUrl() {return url;}
}
