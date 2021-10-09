package com.example.myrecyclerviewapplication;

public class MyListData {
    String name;
    int imgid;

    public MyListData(String name, int imgid) {
        this.name = name;
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

}
