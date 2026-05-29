package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.projections.MovieProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}


/*
SELECT * FROM tb_movie
INNER JOIN tb_genre
ON tb_movie.genre_id = tb_genre.id
WHERE tb_genre.id = 1
*/