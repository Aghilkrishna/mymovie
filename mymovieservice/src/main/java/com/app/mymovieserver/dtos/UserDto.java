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
@ToString(exclude={"password"})
public class UserDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	String password;

	Date dob;

	String mobile;

	String email;

}
