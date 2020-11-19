package com.example.Salsa;

public class Movie {
    private String movietitle, description;
    private int movieposter;

    /*
    *Klasse der kommer til at indeholde film
    *
    * */

    public Movie(String movietitle, String description, int movieposter){
        this.movietitle=movietitle;
        this.description=description;
        this.movieposter=movieposter;
    }



    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovieposter() {
        return movieposter;
    }

    public void setMovieposter(int movieposter) {
        this.movieposter = movieposter;
    }


}
