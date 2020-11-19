package com.example.Salsa.model;

import java.util.ArrayList;

public class GlovVal {

    public static ArrayList<Movie> movie1ArrayList;
    public static ArrayList<Movie> likedmovie;
    private static GlovVal single_instance = null;

    private GlovVal(){};

    public static GlovVal getInstance()
    {
        if (single_instance == null)
            single_instance = new GlovVal();

        return single_instance;


    }

    public static ArrayList<Movie> getMovie1ArrayList() {
        return movie1ArrayList;
    }

    public static void setMovie1ArrayList(ArrayList<Movie> movie1ArrayList) {
        GlovVal.movie1ArrayList = movie1ArrayList;
    }

    public static ArrayList<Movie> getLikedmovie() {
        return likedmovie;
    }

    public static void addLikedmovie(Movie movie) {
        GlovVal.movie1ArrayList.add(movie);
    }

    public static void removemovie(Movie movie){
        movie1ArrayList.remove(movie);
    }



}
