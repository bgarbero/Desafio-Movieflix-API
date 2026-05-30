package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
    SELECT obj FROM Movie obj
    JOIN FETCH obj.genre g
    WHERE (:genreId IS NULL OR g.id = :genreId)
    ORDER BY obj.title
    """)
    Page<Movie> searchMovies(Long genreId, Pageable pageable);

}
