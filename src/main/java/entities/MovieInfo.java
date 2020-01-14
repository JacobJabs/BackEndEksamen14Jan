/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jacob
 */
@Entity
@Table(name = "movieinfo")
public class MovieInfo implements Serializable {

    private String title;
    private float year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;

    public MovieInfo(MovieInfoAll m, ArrayList<MovieRating> s) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.plot = m.getPlot();
        this.directors = m.getDirectors();
        this.genres = m.getGenres();
        this.cast = m.getCast();
        this.poster = m.getPoster();
        this.Scores = s;
    }

    public MovieInfo(MovieInfoAll m) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.plot = m.getPlot();
        this.directors = m.getDirectors();
        this.genres = m.getGenres();
        this.cast = m.getCast();
        this.poster = m.getPoster();
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "movieInfo")
    List<MovieRating> Scores;

    public void setScores(List<MovieRating> Scores) {
        this.Scores = Scores;
    }

    public MovieInfo() {
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieInfo)) {
            return false;
        }
        MovieInfo other = (MovieInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String scoreString = "[";
        for(MovieRating r : Scores){
            scoreString += r.toString() + ",";
        }
        scoreString = scoreString.substring(0, scoreString.length() -1);
        scoreString += "]";
        return "{" + "\"title\":\"" + title + "\", \"year\":\"" + year + "\", \"plot\":\"" + plot + "\", \"directors\":\"" + directors + "\", \"genres\":\"" + genres + "\", \"cast\":\"" + cast + "\", \"poster\":\"" + poster + "\", \"Scores\":" + scoreString + "}";
    }

}
