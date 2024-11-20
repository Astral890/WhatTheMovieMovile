package com.example.whatthemoviemovile;

public class Movie {
    private int id;
    private String title;
    private String[] images;
    private int cont=0;

    public Movie(){
        this.images=new String[4];
    }

    public Movie(int id, String title){
        this.id=id;
        this.title=title;
        images=new String[4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public void addImages(String url){
        this.images[cont++]="https://image.tmdb.org/t/p/original"+url;
    }
}
