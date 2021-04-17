
package com.app.mymovieserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mymovieserver.entities.Movie;


/**
 * @author aghil
 *
 */
public interface MovieRepository extends JpaRepository<Movie, Long>{


}
