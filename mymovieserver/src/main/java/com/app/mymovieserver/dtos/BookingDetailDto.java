package com.app.mymovieserver.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.app.mymovieserver.enums.BookingStatus;
import com.app.mymovieserver.enums.PaymentStatus;
import com.app.mymovieserver.enums.PaymentType;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Data
@ToString
public class BookingDetailDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long id;

	MovieScreenDto movieScreen;

	MovieDto movie;

	Date screeningStartTime;

	Date screeningEndTime;

	PaymentType paymentType;

	PaymentStatus paymentStatus;
	
	BookingStatus bookingStatus;

	UserDto user;

	EmployeeDto employee;

	Set<SeatDto> seats;

	Long createdBy;

	Long modifiedBy;

	Date createdTime;

	Date modifiedTime;



}
