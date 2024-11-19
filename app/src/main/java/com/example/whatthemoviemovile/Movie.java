package com.example.whatthemoviemovile;

public class Movie {
    private int id;
    private String title;
    private String[] images;
    private int cont=0;

    public Movie(int id, String title){
        this.id=id;
        this.title=title;
        images=new String[4];
    }

    public void addImages(String url){
        this.images[cont++]="https://image.tmdb.org/t/p/original"+url;
    }
}
