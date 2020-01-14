/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author jacob
 */
//lavet med https://www.site24x7.com/tools/json-to-java.html
public class MovieScore {

    private String title;
    private String source;
    private float imdbRating;
    private float imdbVotes;
    Viewer viewer;
    Critic critic;
    private float metacritic;

    // Getter Methods 
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "MovieScore{" + "title=" + title + ", source=" + source + ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + ", viewer=" + viewer + ", critics=" + critic + ", metacritic=" + metacritic + '}';
    }

    public float getRating(){
        if(source.equals("tomatoes")){
            return this.critic.getRating();
        } else if(source.equals("imdb")){
            return this.imdbRating;
        } else if(source.equals("metacritic")){
            return this.metacritic/10.0f;
        }
        else
            return 0.0f;
    }
    
    public String getSource() {
        return source;
    }

    public float getMetacritic() {
        return metacritic;
    }

    // Setter Methods 
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setMetacritic(float metacritic) {
        this.metacritic = metacritic;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public Critic getCritic() {
        return critic;
    }

    public void setViewer(Viewer viewerObject) {
        this.viewer = viewerObject;
    }

    public void setCritic(Critic criticObject) {
        this.critic = criticObject;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public float getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(float imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public class Critic {

        private float rating;
        private float numReviews;
        private float meter;

        // Getter Methods 
        public float getRating() {
            return rating;
        }

        public float getNumReviews() {
            return numReviews;
        }

        public float getMeter() {
            return meter;
        }

        // Setter Methods 
        public void setRating(float rating) {
            this.rating = rating;
        }

        public void setNumReviews(float numReviews) {
            this.numReviews = numReviews;
        }

        public void setMeter(float meter) {
            this.meter = meter;
        }
    }

    public class Viewer {

        private float rating;
        private float numReviews;
        private float meter;

        // Getter Methods 
        public float getRating() {
            return rating;
        }

        public float getNumReviews() {
            return numReviews;
        }

        public float getMeter() {
            return meter;
        }

        // Setter Methods 
        public void setRating(float rating) {
            this.rating = rating;
        }

        public void setNumReviews(float numReviews) {
            this.numReviews = numReviews;
        }

        public void setMeter(float meter) {
            this.meter = meter;
        }
    }
}
