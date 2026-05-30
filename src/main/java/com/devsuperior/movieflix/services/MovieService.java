package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;


    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
        return new MovieDetailsDTO(entity);
    }

    public Page<MovieCardDTO> searchMovies(Long genreId, Pageable pageable) {
        Long genreIdParam = (genreId == 0) ? null : genreId;
        Page<Movie> result = repository.searchMovies(genreIdParam, pageable);
        return result.map(MovieCardDTO::new);
    }

}
