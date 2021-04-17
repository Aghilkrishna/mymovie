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
public class MovieScreenDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	Boolean status;

	Date screeningStartTime;

	Date screeningEndTime;

	Long createdBy;

	Long modifiedBy;

	Date createdTime;

	Date modifiedTime;

	MovieDto movieDto;

}
