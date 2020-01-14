package rest;

import com.google.gson.Gson;
import entities.AmountSearched;
import entities.MovieInfo;
import entities.MovieInfoAll;
import entities.MovieRating;
import entities.MovieScore;
import entities.User;
import facades.fetchFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class APIResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private fetchFacade api = new fetchFacade();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            List<User> users = em.createQuery("select user from User user").getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-simple/{title}")
    //@RolesAllowed("user")
    public String getMovie(@PathParam("title") String title) throws ProtocolException, IOException {
        String movieInfo = api.getMovieTitle(title);
        movieInfo = movieInfo.substring(0, movieInfo.length() - 1);
        String moviePoster = api.getMoviePoster(title);
        String[] split = moviePoster.split(",");
        String result = movieInfo + "," + split[1];
        //Gson g = new Gson();
        return result;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-all/{title}")
    @RolesAllowed({"user", "admin"})
    public String getMovieScores(@PathParam("title") String title) throws ProtocolException, IOException {
        Gson g = new Gson();
        if (cache(title)) {
            EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
            EntityManager em = emf.createEntityManager();
            try {
                MovieInfo movie = (MovieInfo) em.createQuery("SELECT a FROM MovieInfo a WHERE a.title = :arg1").setParameter("arg1", title).getSingleResult();
                AmountSearched as = (AmountSearched) em.createQuery("SELECT a FROM AmountSearched a WHERE a.movieInfo = :arg1").setParameter("arg1", movie).getSingleResult();
                as.search();
                em.getTransaction().begin();
                em.persist(as);
                em.getTransaction().commit();
                return movie.toString();
            } finally {
                em.close();
                emf.close();
            }
        } else {
            String json = getMovie(title);
            String imdbScore = api.getMovieIMDBScore(title);
            String tomatoesScore = api.getMovieTomatoesScore(title);
            String metacriticScore = api.getMovieMetaCriticScore(title);
            MovieScore a = g.fromJson(imdbScore, MovieScore.class);
            MovieScore b = g.fromJson(tomatoesScore, MovieScore.class);
            MovieScore c = g.fromJson(metacriticScore, MovieScore.class);
            json = json.substring(0, json.length() - 1);
            json = json + ",\"Scores\":[";
            String result = json + imdbScore + "," + tomatoesScore + "," + metacriticScore + "]}";
            // https://jsonlint.com/
            MovieInfoAll m = g.fromJson(result, MovieInfoAll.class);
            System.out.println(m.getGenres());
            EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            MovieInfo movie = new MovieInfo(m);
            ArrayList<MovieRating> s = new ArrayList<MovieRating>();
            s.add(new MovieRating(movie, a));
            s.add(new MovieRating(movie, b));
            s.add(new MovieRating(movie, c));
            movie.setScores(s);
            AmountSearched as = new AmountSearched(1, movie);
            em.persist(as);
            em.persist(movie);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return result;
        }

    }

    public boolean cache(String title) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        EntityManager em = emf.createEntityManager();
        try {
            List<MovieInfo> m = em.createQuery("SELECT a FROM MovieInfo a WHERE a.title = :arg1").setParameter("arg1", title).getResultList();
            if (m.size() > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            em.close();
            emf.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-count/{title}")
    @RolesAllowed("admin")
    public String AmountMovieSearch(@PathParam("title") String title) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        EntityManager em = emf.createEntityManager();
        try {
            List<MovieInfo> m = em.createQuery("SELECT a FROM MovieInfo a WHERE a.title = :arg1").setParameter("arg1", title).getResultList();
            if (m.size() > 0) {
                AmountSearched as = (AmountSearched) em.createQuery("SELECT a FROM AmountSearched a WHERE a.movieInfo = :arg1").setParameter("arg1", m.get(0)).getSingleResult();

                int amountSearchedCount = as.getAmount();
                String json = "{\"title\":\"" + title + "\",\"searchAmount\":" + amountSearchedCount + "}";
                return json;
            } else {
                return "Movie has not been searched before";
            }

        } finally {
            em.close();
            emf.close();
        }

    }

}
