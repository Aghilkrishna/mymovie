package com.app.mymovieserver.dtos;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@ToString(exclude="password")
@Data
public class LoginDto extends BaseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String email;		

	String password;	

	boolean authenticationStatus;

	String sessionid;

}
