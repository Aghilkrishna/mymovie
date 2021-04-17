package com.app.mymovieserver.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.app.mymovieserver.enums.BookingStatus;
import com.app.mymovieserver.enums.PaymentStatus;
import com.app.mymovieserver.enums.PaymentType;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Entity
@Data
@ToString
public class BookingDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@ManyToOne
	MovieScreen movieScreen;

	@ManyToOne
	Movie movie;

	Date screeningStartTime;

	Date screeningEndTime;

	@Enumerated(EnumType.STRING)
	PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	PaymentStatus paymentStatus;

	@Enumerated(EnumType.STRING)
	BookingStatus bookingStatus;

	@ManyToOne
	User user;

	@ManyToOne
	Employee employee;

	@OneToMany
	Set<Seat> seats;

	Long createdBy;

	Long modifiedBy;

	Date createdTime;

	Date modifiedTime;

	boolean processed = false;

}
