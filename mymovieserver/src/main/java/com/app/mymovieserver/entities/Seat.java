package com.app.mymovieserver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.app.mymovieserver.enums.SeatStatus;
import com.app.mymovieserver.enums.SeatType;

import lombok.Data;
import lombok.ToString;

/**
 * @author aghil
 *
 */
@Entity
@Data
@ToString
public class Seat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	int seatRow;

	int seatNumber;

	@Enumerated(EnumType.STRING)
	SeatType seatType;

	@Enumerated(EnumType.STRING)
	SeatStatus seatStatus;

	@ManyToOne
	MovieScreen movieScreen;

	@Version
	long version;

}
