package com.app.mymovieserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.mymovieserver.dtos.BookingDetailDto;

/**
 * @author aghil
 *
 */
@RestController
@RequestMapping("/ticket")
public class BookingController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(BookingController.class);

	@RequestMapping(value="/block",method =RequestMethod.POST)
	public BookingDetailDto blockSeats(@RequestBody BookingDetailDto bookingDetailDto){
		log.info("Start BookingController blockSeats method");
		bookingDetailDto = (BookingDetailDto) sendtoMQ(bookingDetailDto, "blockSeats", "bookingService");
		log.info("End BookingController blockSeats method");
		return bookingDetailDto;

	}

	@RequestMapping(value="/confirm",method =RequestMethod.POST)
	public BookingDetailDto confirmBooking(@RequestBody BookingDetailDto bookingDetailDto){
		log.info("Start BookingController confirmBooking method");
		bookingDetailDto = (BookingDetailDto) sendtoMQ(bookingDetailDto, "confirmBooking", "bookingService");
		log.info("End BookingController confirmBooking method");
		return bookingDetailDto;

	}

	@RequestMapping(value="/cancel",method =RequestMethod.POST)
	public BookingDetailDto cancelBooking(@RequestBody BookingDetailDto bookingDetailDto){
		log.info("Start BookingController cancelBooking method");
		bookingDetailDto = (BookingDetailDto) sendtoMQ(bookingDetailDto, "cancelBooking", "bookingService");
		log.info("End BookingController cancelBooking method");
		return bookingDetailDto;

	}

}
