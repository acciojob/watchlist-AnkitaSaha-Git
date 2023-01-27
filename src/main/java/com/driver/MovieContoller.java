package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieContoller {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String s= movieService.addMovie(movie);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String s= movieService.addDirector(director);
        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestBody String movieName, String directorName ){
        String s= movieService.addMovieDirectorPair(movieName,directorName);
        return  new ResponseEntity<>(s,HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable ("name") String name){
        Movie m=movieService.getMovieByName(name);
        return new ResponseEntity<>(m,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director d= movieService.getDirectorByName(name);
        return new ResponseEntity<>(d,HttpStatus.FOUND);
    }

    @GetMapping("movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName (@PathVariable ("director") String name){
        List<String> s= movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(s,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> res= movieService.findAllMovies();
        return new ResponseEntity<>(res,HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        String s= movieService.deleteDirectorByName(name);
        return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String s= movieService.deleteAllDirector();
        return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }
}
