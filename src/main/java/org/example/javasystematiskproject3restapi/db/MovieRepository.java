package org.example.javasystematiskproject3restapi.db;

import jakarta.enterprise.context.ApplicationScoped;

/*
This class is a singleton in the application,only one instance of this class is shared across the application.
 */

import jakarta.persistence.EntityManager; //It uses JPA object to interact with database such as running SQL Queries
// or saving data
import jakarta.persistence.PersistenceContext;//Injects entity manager to handle database operations
import jakarta.persistence.Query;
import jakarta.transaction.Transactional; //All methods in this class will participate in database transactions
import org.example.model.Movie;

import java.util.List; //Collection of movies


@ApplicationScoped
@Transactional
public class MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> findMovies() {
        String sql = "SELECT * FROM movies"; //selects every row and column from database
        Query query = entityManager.createNativeQuery(sql);
        List<Movie> movie = query.getResultList();
        return movie;
    }

    /**
     * findMovies is a method to fetch a list of the movies from the table movies
     * Steps involved:
     * sql query is defined to select all the rows from the movie table
     * Then 2nd step creates query object from SQL statement
     * The result is retrieved as a list of movies
     * Returns a list of all the movies
     */

    public void create(Movie movie) {
        entityManager.persist(movie);
    }

    /**
     * It is a method to create a movie in the database
     * entity manager.persist(movie) saves the movie object to database and movie object should already have its property set
     * before calling this method
     */

    public Movie findById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    /**
     * Looks for a movie in the database using its ID.
     * entityManager.find(Movie.class, id):
     * Searches the movies table for a row with the given id.
     * Returns the movie if found, otherwise returns null.
     */

    public Movie update(Movie movie) {
        return entityManager.merge(movie);
    }

    /**
     * Updates an existing movie in the database.
     * entityManager.merge(movie):
     * Updates the movie's details in the database.
     * Returns the updated movie object.
     */

    public void delete(Long id) {
        Movie movie = findById(id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }

    /**
     *     Deletes a movie from the database by using its ID.
     *     findById(id) checks if a movie with the given id exists.
     *     If it exists, entityManager.remove(movie) deletes it.
     */

}

