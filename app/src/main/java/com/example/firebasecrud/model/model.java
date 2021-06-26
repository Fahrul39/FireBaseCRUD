package com.example.firebasecrud.model;

public class model {
    String judul,desc,purl;
    model(){

    }
    public model(String judul, String desc, String purl) {
        this.judul = judul;
        this.desc = desc;
        this.purl = purl;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
