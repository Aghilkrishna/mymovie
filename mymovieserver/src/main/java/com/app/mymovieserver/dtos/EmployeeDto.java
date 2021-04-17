package com.app.mymovieserver.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Data
@ToString
public class EmployeeDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	Date dob;

	String mobile;

	String email;

	TheatreDto threatreDto;

}
