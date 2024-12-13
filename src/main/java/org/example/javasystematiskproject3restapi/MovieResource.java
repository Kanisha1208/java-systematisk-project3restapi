package org.example.javasystematiskproject3restapi;

//This class imports all libraries and classes to build restful API

import jakarta.inject.Inject;

/**
 * Dependency injection to provide objects to this class automatically without creating them manually
 */

import jakarta.ws.rs.*;

/**
 * Annotation for creating restful API's, which are services that allows communication between the website
 * and the web server
 */

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 Specifies the format of the data being sent or received in the API, which is in the form of requests and responses
 JSON format or plain text
 */

import org.example.javasystematiskproject3restapi.db.MovieRepository;

/**
 * Helper class that performs database operations (CRUD) and manages database
 */

import org.example.model.Movie; //Entity class that defines properties of movie and maps them to database table

import java.util.List; //Collection of movies



@Path ("/movies") //Base URL path for all endpoints in this class
public class MovieResource {
@Inject

/**
 * Dependency Injection
 * Automatically injects an instance of movie repository,no need to create manually
 */

    private MovieRepository movieRepository;



    @GET  //Handles HTTP GET request to fetch all resources/movies
    @Produces(MediaType.APPLICATION_JSON) //Specifies that the response will be in JSON format
    public List<Movie> getMovie() {
        return movieRepository.findMovies(); //Fetches list of all movies from database
    }




    @POST  //Handles HTTP Post request to create a new resource/new movie
    @Consumes(MediaType.APPLICATION_JSON) //Specifies that the input/request will be in JSON format
    @Produces(MediaType.TEXT_PLAIN)  //Specifies that the response will be in JSON format
    public Response createMovie(Movie movie) {
        movieRepository.create(movie); //Save the movie object to the database
        return Response.status(Response.Status.CREATED)
                .entity("movie created")
                .build();
        /**
         * Builds HTTP response with status 201 created
         * and plain text message movie created
         */
    }




    @GET //Handles HTTP GET request to fetch a resource/movie by using ID
    @Path("/{id}")
    /**
     * Makes the URL dynamic so the client can pass a movie's ID.
     * Example:
     * If the URL is /movies/5, the id will be 5.
     * The {id} part in the URL corresponds to the method's parameter.
      */
    @Consumes(MediaType.APPLICATION_JSON)  //Ensures the request is in JSON Format
    @Produces(MediaType.APPLICATION_JSON) //Ensures the response comes back in JSON Format
    public Response getMovieBYId(@PathParam("id") Long id ) {
        /**
         *  Extracts the movie ID ({id}) from the URL and assigns it to the variable id which is of the Datatype long
         *  and long is used because IDs are usually large numbers.
         */
        Movie movie = movieRepository.findById(id); //Checks if the movie with given ID exists
        if(movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); //If not found responds with 404 not found
        }
        return Response.status(Response.Status.OK). //If found,returns the movie with status 200 OK
                entity(movie).build();
    }




    @Path("/{id}")
    @PUT // Handles HTTP PUT requests to update an existing resource/movie
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") Long id, Movie updatedmovie){
        Movie movie = movieRepository.findById(id);
        if(movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        updatedmovie.setId(movie.getId());
        movieRepository.update(updatedmovie); //Updates movie with the given ID
        return Response.status(Response.Status.OK).build();

        /**
         * Set the ID of the updatedmovie to match the original movie's ID:
         * This ensures the correct movie is updated in the database.
         * Example:
         * Original Movie: { "id": 5, "title": "Old Title", "genre": "Drama" }
         * Updated Data sent by the Client: { "title": "New Title", "genre": "Adventure" }
         * After setId: { "id": 5, "title": "New Title", "genre": "Adventure" }
         *
         * Step 2: Call movieRepository.update(updatedmovie):
         * This updates the movie in the database.
         */
    }


    @Path("/{id}")
    @DELETE //Handles HTTP Delete requests to delete a resource/ a movie by using its ID
    @Produces (MediaType.APPLICATION_JSON) //Response in JSON format
    @Consumes(MediaType.APPLICATION_JSON)  //Input in JSON format
    public Response deleteMovie(@PathParam("id") Long id) {
        Movie movie = movieRepository.findById(id); //Checks if movie exists
        if(movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); //If not responds with 404 not found
        }
        movieRepository.delete(id); //Deletes the movie
        return Response.status(Response.Status.NO_CONTENT).build(); //Responds with 204 no content

        /**
         * Sends an HTTP 204 No Content response.
         * This status means:
         * The delete operation was successful.
         * There’s no additional information to return in the response body.
         */
    }


    } //Movie Resource class ends


