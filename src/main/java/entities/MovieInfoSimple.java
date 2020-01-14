/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author jacob
 */
public class MovieInfoSimple {
// https://www.site24x7.com/tools/json-to-java.html
    private String title;
    private float year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    
    // Getter Methods 
    public String getTitle() {
        return title;
    }

    public float getYear() {
        return year;
    }

    public String getPlot() {
        return plot;
    }

    public String getDirectors() {
        return directors;
    }

    public String getGenres() {
        return genres;
    }

    public String getCast() {
        return cast;
    }

    public String getPoster() {
        return poster;
    }

    // Setter Methods 
    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(float year) {
        this.year = year;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
