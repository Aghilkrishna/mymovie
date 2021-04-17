
package com.app.mymovieserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.mymovieserver.entities.User;


/**
 * @author aghil
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByMobile(String mobile);

}
