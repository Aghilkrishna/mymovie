
package com.app.mymovieserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mymovieserver.entities.MovieScreen;


/**
 * @author aghil
 *
 */
public interface MovieScreenRepository extends JpaRepository<MovieScreen, Long>{

	MovieScreen findById(long id);

}
