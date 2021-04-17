package com.app.mymovieserver.dtos;

import java.io.Serializable;

import com.app.mymovieserver.enums.SeatStatus;
import com.app.mymovieserver.enums.SeatType;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Data
@ToString
public class SeatDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	int row;

	int seatNumber;

	SeatType seatType;

	SeatStatus seatStatus;

	MovieScreenDto movieScreenDto;

}
