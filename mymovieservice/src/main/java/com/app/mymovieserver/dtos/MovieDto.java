package com.app.mymovieserver.dtos;

import java.io.Serializable;

import com.app.mymovieserver.enums.MovieStatus;
import com.app.mymovieserver.enums.MovieType;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Data
@ToString
public class MovieDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	String director;

	String description;

	int durationInMins;

	TheatreDto theatreDto;

	MovieType movieType;

	MovieStatus movieStatus;

}
