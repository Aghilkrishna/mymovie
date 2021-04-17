package com.app.mymovieserver.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Entity
@Data
@ToString
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;

	Date dob;

	@Column(name = "mobile",unique=true,nullable=false)
	String mobile;

	@Column(name = "email",unique=true,nullable=false)
	String email;

	@ManyToOne
	Theatre threatre;

}
