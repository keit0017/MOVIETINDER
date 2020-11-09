package com.example.movietinder;

import java.util.ArrayList;

class GlovVal {

    public static ArrayList<Movie> movie1ArrayList;
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
}
