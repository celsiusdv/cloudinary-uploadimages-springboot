package com.imagesupload;

import jakarta.persistence.*;

@Entity
@Table(name = "image_entity")
public class ImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(2048)")
    private String url;

    private String name;

    public ImageEntity(){}
    public ImageEntity(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void setId(Integer id) {this.id = id;}
    public void setUrl(String url) {this.url = url;}
    public void setName(String name) {this.name = name;}
    public Integer getId() {return id;}
    public String getUrl() {return url;}
    public String getName() {return name;}
}
