package com.app.mymovieserver.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Data
@ToString
public class TheatreDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	String address;

	int rating;

	List<MovieScreenDto> movieScreenDtos;

	List<EmployeeDto> employeeDtos;

}
