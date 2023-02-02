package com.example.denememesaj.ui;

import java.util.List;
import java.util.Stack;

public class GroupModel {

    //model classında dinamik verilerin depolanmasını sağlıyoruz.

    private String name,description,image,uid;
    public List<String>numbers;
    public GroupModel(String name ,String description,String image,List<String>numbers,String uid){
        this.name=name;
        this.description=description;
        this.image=image;
        this.numbers=numbers;
        this.uid=uid;
    }
    public  String getName(){
        return  name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
