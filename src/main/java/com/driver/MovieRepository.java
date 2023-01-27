package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movieMap;
    HashMap<String, Director> directorMap;

    HashMap<String,List<String>> pairMap;

    public MovieRepository() {
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.pairMap = new HashMap<>();
    }

    public String addMovie(Movie movie){
        String m= movie.getName();
        movieMap.put(m,movie);
        return "Added successfully";
    }

    public String addDirector(Director director){
        String m= director.getName();
        directorMap.put(m,director);
        return "Added successfully";
    }

    public String addMovieDirectorPair(String movieName,String directorName) {
        if (!movieMap.containsKey(movieName) || !directorMap.containsKey(directorName))
            return "Movie or Director not found";
        if (pairMap.containsKey(directorName)) {
            pairMap.get(directorName).add(movieName);
        } else {
            List<String> ans = new ArrayList<>();
            ans.add(movieName);
            pairMap.put(directorName, ans);
        }
        return "Movie_Director Pair Added Successfully";
    }

    public Movie getMovieByName(String name){
        if(movieMap.containsKey(name))
            return movieMap.get(name);
        else return null;

    }

    public Director  getDirectorByName(String name){
        if(directorMap.containsKey(name))
            return directorMap.get(name);
        else return null;
    }

    public List<String> getMoviesByDirectorName(String name){
        if(pairMap.containsKey(name)){
            return pairMap.get(name);
        }
        else return null;
    }

    public List<String> findAllMovies(){
        List<String > ans= new ArrayList<>();
        for(String m: movieMap.keySet()){
            ans.add(m);
        }
        return  ans;
    }

    public String deleteDirectorByName(String name){
        if(pairMap.containsKey(name))
            pairMap.remove(name);
        return "Successfully deleted";
    }

    public String deleteAllDirectors(){
        for (String dir: pairMap.keySet()) {
            List<String> lis = pairMap.get(dir);
            for (String movie : lis) {
                if (movieMap.containsKey(movie))
                    movieMap.remove(movie);

            }

            directorMap.remove(dir);
        }
        for(String d:directorMap.keySet()){
            directorMap.remove(d);
        }
        return "All directors and all of their movies removed successfully";
    }
}
