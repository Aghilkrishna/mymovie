
package com.app.mymovieserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mymovieserver.entities.MovieScreen;
import com.app.mymovieserver.entities.Seat;


/**
 * @author aghil
 *
 */
public interface SeatRepository extends JpaRepository<Seat, Long>{

	Seat findByIdAndMovieScreen(long id, MovieScreen movieScreen);

}
