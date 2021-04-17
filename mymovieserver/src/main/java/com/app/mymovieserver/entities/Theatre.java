package com.app.mymovieserver.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Entity
@Data
@ToString
public class Theatre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;

	String address;

	int rating;

	@OneToMany
	List<MovieScreen> movieScreens;

	@OneToMany
	List<Employee> employees;

}
