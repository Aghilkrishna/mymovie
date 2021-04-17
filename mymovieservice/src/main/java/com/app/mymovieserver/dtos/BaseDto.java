package com.app.mymovieserver.dtos;

import lombok.Data;
import lombok.ToString;


/**
 * @author aghil
 *
 */
@Data
@ToString
public class BaseDto {


	public BaseDto(){
	}

	public BaseDto(int code){
		this.statusCode=code;
	}

	int statusCode=2000;

	String trackId;	

	String errorDescription; 
}
