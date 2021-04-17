
package com.app.mymovieserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mymovieserver.entities.BookingDetail;


/**
 * @author aghil
 *
 */
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long>{

	BookingDetail findById(long id);

	List<BookingDetail> findByProcessed(boolean processed);
}
