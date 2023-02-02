package com.example.denememesaj;

public class grupslidemodel {

    String name;
    String description;
    String img_url;
    int sıra;

    public grupslidemodel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getSıra() {
        return sıra;
    }

    public void setSıra(int sıra) {
        this.sıra = sıra;
    }

    public grupslidemodel(String name, String description, String img_url, int sıra) {
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.sıra = sıra;
    }
}
