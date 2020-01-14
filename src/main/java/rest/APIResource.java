package rest;

import com.google.gson.Gson;
import entities.User;
import facades.fetchFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    //@RolesAllowed("user")
    public String getMovieScores(@PathParam("title") String title) throws ProtocolException, IOException {
        
        String json = getMovie(title); 
        String imdbScore = api.getMovieIMDBScore(title); 
        String tomatoesScore = api.getMovieTomatoesScore(title); 
        String metacriticScore = api.getMovieMetaCriticScore(title); 
        json = json.substring(0, json.length() - 1); 
        json = json + ",\"Scores\":["; 
        String result = json + imdbScore + "," + tomatoesScore + "," + metacriticScore + "]}";
        // https://jsonlint.com/
        return result;
        
    }
    
    
}
