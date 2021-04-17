package com.app.mymovieserver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.app.mymovieserver.enums.MovieStatus;
import com.app.mymovieserver.enums.MovieType;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Entity
@Data
@ToString
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;

	String director;

	String description;

	int durationInMins;

	@ManyToOne
	Theatre theatre;

	@Enumerated(EnumType.STRING)
	MovieType movieType;

	@Enumerated(EnumType.STRING)
	MovieStatus movieStatus;

}
