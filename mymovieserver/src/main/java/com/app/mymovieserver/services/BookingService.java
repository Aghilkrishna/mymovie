package com.app.mymovieserver.services;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.app.mymovieserver.dtos.BookingDetailDto;
import com.app.mymovieserver.dtos.SeatDto;
import com.app.mymovieserver.entities.BookingDetail;
import com.app.mymovieserver.entities.Movie;
import com.app.mymovieserver.entities.MovieScreen;
import com.app.mymovieserver.entities.Seat;
import com.app.mymovieserver.entities.User;
import com.app.mymovieserver.enums.BookingStatus;
import com.app.mymovieserver.enums.PaymentStatus;
import com.app.mymovieserver.enums.SeatStatus;
import com.app.mymovieserver.exceptions.ErrorCodeDescription;
import com.app.mymovieserver.exceptions.RestException;
import com.app.mymovieserver.repository.BookingDetailRepository;
import com.app.mymovieserver.repository.MovieRepository;
import com.app.mymovieserver.repository.MovieScreenRepository;
import com.app.mymovieserver.repository.SeatRepository;
import com.app.mymovieserver.repository.TheatreRepository;
import com.app.mymovieserver.repository.UserRepository;

/**
 * @author aghil
 *
 */
@Service
public class BookingService {

	private static final Logger log = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	BookingDetailRepository bookingDetailRepository;

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	MovieScreenRepository movieScreenRepository;

	@Autowired
	TheatreRepository theatreRepository;

	@Autowired
	UserRepository userRepository;

	@Value("${seat.block.intervals.in.seconds}")
	int unblocksSeatIntervals;

	@Transactional
	public BookingDetailDto blockSeats(BookingDetailDto bookingDetailDto) {
		log.info("Start BookingService blockSeats method");
		try {

			if(bookingDetailDto != null) {

				Optional<MovieScreen> msOpt = movieScreenRepository.findById(bookingDetailDto.getMovieScreen().getId());
				Optional<User> userOpt = userRepository.findById(bookingDetailDto.getUser().getId());

				if(!userOpt.isPresent())
					throw new RestException(ErrorCodeDescription.ERROR_INVALID_USER);

				if(bookingDetailDto.getSeats().size() > 6)
					throw new RestException(ErrorCodeDescription.ERROR_MAX_ALLOWED_SEAT_EXCEEDS);

				if(msOpt.isPresent()) {
					MovieScreen ms = msOpt.get();
					Movie m = ms.getMovie();

					BookingDetail bd = new BookingDetail();
					bd.setBookingStatus(BookingStatus.INPROGRESS);
					bd.setCreatedBy(userOpt.get().getId());
					bd.setCreatedTime(new Date());
					bd.setMovie(m);
					bd.setMovieScreen(ms);
					bd.setScreeningEndTime(ms.getScreeningEndTime());
					bd.setScreeningStartTime(ms.getScreeningStartTime());
					bd.setUser(userOpt.get());

					Set<Seat> seats = new LinkedHashSet<Seat>();

					for(SeatDto eachSeatDto : bookingDetailDto.getSeats()) {
						Seat eachSeat = seatRepository.findByIdAndMovieScreen(eachSeatDto.getId(), ms);

						if(eachSeat == null)
							throw new RestException(ErrorCodeDescription.ERROR_INVALID_SEAT);

						if(!eachSeat.getSeatStatus().equals(SeatStatus.OPEN))
							throw new RestException(ErrorCodeDescription.ERROR_SEAT_NOT_OPEN);

						eachSeat.setSeatStatus(SeatStatus.BLOCKED);
						seats.add(eachSeat);
					}

					bd.setSeats(seats);

					bd = bookingDetailRepository.saveAndFlush(bd);

					bookingDetailDto.setId(bd.getId());
					bookingDetailDto.setStatusCode(0);

					final BookingDetail bd2 = bd;

					ScheduledExecutorService scheduledExecutorService =
							Executors.newScheduledThreadPool(1);

					ScheduledFuture scheduledFuture =
							scheduledExecutorService.schedule(new Callable() {
								public Object call() throws Exception {
									unblockSeatsAfterTimeoutOrShowEnd(bd2, BookingStatus.TIMEDOUT);
									return "Called!";
								}
							},
									unblocksSeatIntervals,
									TimeUnit.SECONDS);

					scheduledExecutorService.shutdown();

				}else{
					throw new RestException(ErrorCodeDescription.ERROR_INVALID_MOVIESCREEN);
				}
			}else {
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_PARAMETERS);
			}
			log.info("bookingDetailDto : "+bookingDetailDto);

		}catch (RestException e) {
			log.error("blockSeats Error",e);
			bookingDetailDto.setStatusCode(e.getErrorCodeDescription().getErrorCode());
			bookingDetailDto.setErrorDescription(e.getErrorCodeDescription().getErrorDescription());
		} catch (ObjectOptimisticLockingFailureException e) {
			log.error("blockSeats Error",e);
			bookingDetailDto.setStatusCode(ErrorCodeDescription.ERROR_SEAT_BOOKED_BY_OTHER.getErrorCode());
			bookingDetailDto.setErrorDescription(ErrorCodeDescription.ERROR_SEAT_BOOKED_BY_OTHER.getErrorDescription());
		} catch (DataIntegrityViolationException e) {
			log.error("blockSeats Error",e);
			bookingDetailDto.setStatusCode(ErrorCodeDescription.ERROR_SEAT_BOOKED_BY_OTHER.getErrorCode());
			bookingDetailDto.setErrorDescription(ErrorCodeDescription.ERROR_SEAT_BOOKED_BY_OTHER.getErrorDescription());
		} catch (Exception e) {
			log.error("blockSeats Error",e);
			bookingDetailDto.setStatusCode(ErrorCodeDescription.ERROR_GENERIC.getErrorCode());
		}  

		log.info("End BookingService blockSeats method");
		return bookingDetailDto;
	}

	@Transactional
	public BookingDetailDto confirmBooking(BookingDetailDto bookingDetailDto) {
		log.info("Start BookingService confirmBooking method");
		try {

			if(bookingDetailDto == null || bookingDetailDto.getId() == null)
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_PARAMETERS);

			BookingDetail bd = bookingDetailRepository.findById(bookingDetailDto.getId().longValue());

			if(bd.getBookingStatus().equals(BookingStatus.TIMEDOUT))
				throw new RestException(ErrorCodeDescription.ERROR_BOOKING_TIMEOUT);

			if(bd.getBookingStatus().equals(BookingStatus.CANCELLED))
				throw new RestException(ErrorCodeDescription.ERROR_BOOKING_CANCELLED);


			if(bd.getBookingStatus().equals(BookingStatus.INPROGRESS)) {
				bd.setBookingStatus(BookingStatus.BOOKED);
				bd.setModifiedTime(new Date());
				bd.setPaymentStatus(PaymentStatus.PAID);
				bd.setPaymentType(bookingDetailDto.getPaymentType());

				Set<Seat> seats = bd.getSeats();

				for(Seat eachSeat : seats) {
					eachSeat.setSeatStatus(SeatStatus.BOOKED);
				}

				bd.setSeats(seats);

				bd = bookingDetailRepository.save(bd);

				bookingDetailDto.setStatusCode(0);
			}

			log.info("bookingDetailDto : "+bookingDetailDto);

		}catch (RestException e) {
			log.error("confirmBooking Error",e);
			bookingDetailDto.setStatusCode(e.getErrorCodeDescription().getErrorCode());
			bookingDetailDto.setErrorDescription(e.getErrorCodeDescription().getErrorDescription());
		}  catch (Exception e) {
			log.error("confirmBooking Error",e);
			bookingDetailDto.setStatusCode(ErrorCodeDescription.ERROR_GENERIC.getErrorCode());
		}  

		log.info("End BookingService confirmBooking method");
		return bookingDetailDto;
	}

	@Transactional
	public BookingDetailDto cancelBooking(BookingDetailDto bookingDetailDto) {
		log.info("Start BookingService cancelBooking method");
		try {

			if(bookingDetailDto == null || bookingDetailDto.getId() == null)
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_PARAMETERS);

			BookingDetail bd = bookingDetailRepository.findById(bookingDetailDto.getId().longValue());

			if(bd == null)
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_BOOKING);

			if(!bd.getBookingStatus().equals(BookingStatus.SHOWEND) && !bd.getBookingStatus().equals(BookingStatus.TIMEDOUT)) {
				bd.setBookingStatus(BookingStatus.CANCELLED);
				bd.setModifiedTime(new Date());

				Set<Seat> seats = bd.getSeats();

				for(Seat eachSeat : seats) {
					eachSeat.setSeatStatus(SeatStatus.OPEN);
				}

				seatRepository.saveAll(seats);

				bd.setSeats(null);

				bd = bookingDetailRepository.save(bd);

				bookingDetailDto.setStatusCode(0);
			}

			log.info("bookingDetailDto : "+bookingDetailDto);

		}catch (RestException e) {
			log.error("cancelBooking Error",e);
			bookingDetailDto.setStatusCode(e.getErrorCodeDescription().getErrorCode());
			bookingDetailDto.setErrorDescription(e.getErrorCodeDescription().getErrorDescription());
		}  catch (Exception e) {
			log.error("cancelBooking Error",e);
			bookingDetailDto.setStatusCode(ErrorCodeDescription.ERROR_GENERIC.getErrorCode());
		}  

		log.info("End BookingService cancelBooking method");
		return bookingDetailDto;
	}

	@Transactional
	public void unblockSeatsAfterTimeoutOrShowEnd(BookingDetail bd, BookingStatus bookingStatus) {
		log.info("Start BookingService unblockSeatsAfterTimeoutOrShowEnd method");
		try {

			if(bd == null)
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_BOOKING);

			bd.setBookingStatus(bookingStatus);
			bd.setModifiedTime(new Date());

			Set<Seat> seats = bd.getSeats();

			for(Seat eachSeat : seats) {
				eachSeat.setSeatStatus(SeatStatus.OPEN);
			}

			seatRepository.saveAll(seats);

			bd.setSeats(null);

			if(bookingStatus.equals(BookingStatus.SHOWEND))
				bd.setProcessed(true);

			bd = bookingDetailRepository.save(bd);

		}catch (RestException e) {
			log.error("unblockSeatsAfterTimeoutOrShowEnd Error",e);
		}  catch (Exception e) {
			log.error("unblockSeatsAfterTimeoutOrShowEnd Error",e);
		}  

		log.info("End BookingService unblockSeatsAfterTimeoutOrShowEnd method");
	}

	@Scheduled(fixedDelayString = "${sch.unblock.seat.after.show.intervals.milliseconds}")
	@Transactional
	public void unblockSeatAfterShowEnd() {
		log.info("Start BookingService unblockSeatAfterShowEnd method");

		List<BookingDetail> bds = bookingDetailRepository.findByProcessed(false);

		Date currentDate = new Date();

		for(BookingDetail eachBd : bds) {

			if(currentDate.after(eachBd.getScreeningEndTime()) && eachBd.getBookingStatus().equals(BookingStatus.BOOKED)) {
				unblockSeatsAfterTimeoutOrShowEnd(eachBd, BookingStatus.SHOWEND);
			}
		}

		log.info("End BookingService unblockSeatAfterShowEnd method");
	}

}
