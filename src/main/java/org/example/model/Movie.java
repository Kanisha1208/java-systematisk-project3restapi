package org.example.model;


import jakarta.persistence.*; //Imports classes and interfaces from JPA to work with data in databases


@Entity  //Marks movie class as Entity class,that can be mapped to the database table.
@Table(name="movies") //Name of the database table
public class Movie {
    public Movie() { //Constructor with no argument required by JPA for creating objects or the structure of
        // database table
    }


    @Id // Primary key of entity class
@GeneratedValue(strategy = GenerationType.IDENTITY) //Primary key value is generated by JPA automatically(Auto-increment)
    private long id; //Unique identifier for each movie record
    private String title;
    private String genre;
    @Column(name = "producedYear", nullable = false) //Defined non-null constraint for the column

    /**
     * JPA maps entity class to database table and fields in java class to corresponding database columns
     * Nullable = false ensures that this field or column can't be empty otherwise it will throw errors
     */

    private int producedYear;
    private String description;
    private String director;

    /*
    The above attributes stores movie title,genre,year the movie got released,
    summary of the movie and name of the director

     */



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getProducedYear() {
        return producedYear;
    }

    public void setProducedYear(int producedYear) {
        this.producedYear = producedYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}

/*
Getter and Setter methods:
Getters: Retrieve field values
Setters: Update field values
 */