/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author artin
 */
public class fetchFacade {

//    public String getAllDataInParalelWithQueue() throws ProtocolException, IOException, InterruptedException, ExecutionException
//    {
//        ExecutorService workingJack = Executors.newCachedThreadPool();
//        List<Future<String>> futureList = new ArrayList();
//        for (int index = 1; index < 119; index++)
//        {
//            final int i = index;
//            Future<String> future = workingJack.submit(() -> getMovieData(i));
//            futureList.add(future);
//        }
//        StringBuilder sb = new StringBuilder("[");
//        for (Future<String> future : futureList)
//        {
//            sb.append(future.get() + ",");
//        }
//        sb.append("]");
//        return sb.toString();
//
//    }
    public String getMovieTitle(String title) throws MalformedURLException, ProtocolException, IOException {
        // String fullUrl = "http://dnd5eapi.co/api/spells/" + index;//"/?format=json";
        String fullUrl = "http://exam-1187.mydemos.dk/movieInfo/" + title;
        URL url = new URL(fullUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream())) {
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            return jsonStr;
        }
    }

    public String getMoviePoster(String title) throws MalformedURLException, ProtocolException, IOException {
        // String fullUrl = "http://dnd5eapi.co/api/spells/" + index;//"/?format=json";
        String fullUrl2 = "http://exam-1187.mydemos.dk/moviePoster/" + title;
        URL url = new URL(fullUrl2);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream())) {
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            return jsonStr;
        }
    }

    public String getMovieIMDBScore(String title) throws MalformedURLException, ProtocolException, IOException {
        // String fullUrl = "http://dnd5eapi.co/api/spells/" + index;//"/?format=json";
        String fullUrl3 = "http://exam-1187.mydemos.dk/imdbScore/" + title;
        URL url = new URL(fullUrl3);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream())) {
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            return jsonStr;
        }
    }

    public String getMovieTomatoesScore(String title) throws MalformedURLException, ProtocolException, IOException {
        // String fullUrl = "http://dnd5eapi.co/api/spells/" + index;//"/?format=json";
        String fullUrl4 = "http://exam-1187.mydemos.dk/tomatoesScore/" + title;
        URL url = new URL(fullUrl4);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream())) {
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            return jsonStr;
        }
    }

    public String getMovieMetaCriticScore(String title) throws MalformedURLException, ProtocolException, IOException {
        // String fullUrl = "http://dnd5eapi.co/api/spells/" + index;//"/?format=json";
        String fullUrl5 = "http://exam-1187.mydemos.dk/metacriticScore/" + title;
        URL url = new URL(fullUrl5);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream())) {
            String jsonStr = "";
            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            return jsonStr;
        }
    }

    public static void main(String[] args) throws ProtocolException, IOException, InterruptedException, ExecutionException {
        fetchFacade facade = new fetchFacade();
//        String satan = facade.getDnDData(1);
//        
//        System.out.println(facade.getDnDData(1));
        String result = facade.getMovieTitle("Die%20Hard");
        System.out.println(result);
    }
}
