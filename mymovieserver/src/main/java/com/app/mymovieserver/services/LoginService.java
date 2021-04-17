package com.app.mymovieserver.services;

import org.jasypt.digest.StringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mymovieserver.dtos.LoginDto;
import com.app.mymovieserver.dtos.UserDto;
import com.app.mymovieserver.entities.User;
import com.app.mymovieserver.exceptions.ErrorCodeDescription;
import com.app.mymovieserver.exceptions.RestException;
import com.app.mymovieserver.repository.UserRepository;

/**
 * @author aghil
 *
 */
@Service
public class LoginService {

	private static final Logger log = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	StringDigester stringDigester;

	@Autowired
	UserRepository userRepository;

	public LoginDto validateUser(LoginDto loginDto) {

		try {
			log.info("Request : "+loginDto);
			if(loginDto != null && loginDto.getEmail() != null && loginDto.getEmail().trim().length() > 0
					&& loginDto.getPassword() != null && loginDto.getPassword().trim().length() > 0) {

				User user = userRepository.findByEmail(loginDto.getEmail().trim());
				if (user != null) {

					boolean result = stringDigester.matches(loginDto.getPassword(), user.getPassword());
					log.info(" Password Verified Status : "+result);
					if (result == true) {

						loginDto.setAuthenticationStatus(true);					
						loginDto.setStatusCode(0);
					}
					else {
						loginDto.setStatusCode(ErrorCodeDescription.LOGIN_VALIDTE.getErrorCode());
					}
				}
				else{
					loginDto.setStatusCode(ErrorCodeDescription.LOGIN_VALIDTE.getErrorCode());
				}
			}else {
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_PARAMETERS);
			}

		} catch (RestException e) {
			log.error("login Error",e);
			loginDto.setStatusCode(e.getErrorCodeDescription().getErrorCode());
			loginDto.setErrorDescription(e.getErrorCodeDescription().getErrorDescription());
		}  catch (Exception e) {
			log.error("login Error",e);
			loginDto.setStatusCode(ErrorCodeDescription.ERROR_GENERIC.getErrorCode());
		}        
		log.info("Login Response : " + loginDto);
		return loginDto;
	}

	public UserDto createUser(UserDto userDto) {

		try {
			log.info("Request : "+userDto);

			if(userDto != null && userDto.getEmail() != null && userDto.getEmail().trim().length() > 0
					&& userDto.getMobile() != null && userDto.getMobile().trim().length() > 0) {

				User user = userRepository.findByEmail(userDto.getEmail().trim());

				if(user == null) {
					user = userRepository.findByMobile(userDto.getMobile().trim());

					if(user == null) {
						user = new User();
						user.setDob(userDto.getDob());
						user.setEmail(userDto.getEmail());
						user.setMobile(userDto.getMobile());
						user.setName(userDto.getName());

						String pwd = stringDigester.digest(userDto.getPassword());

						user.setPassword(pwd);

						userRepository.save(user);
						userDto.setStatusCode(0);
					}else {
						throw new RestException(ErrorCodeDescription.ERROR_SIGNUP_USER_MOBILE_EXIST);
					}
				}else {
					throw new RestException(ErrorCodeDescription.ERROR_SIGNUP_USER_EMAIL_EXIST);
				}
			}else {
				throw new RestException(ErrorCodeDescription.ERROR_INVALID_PARAMETERS);
			}

		} catch (RestException e) {
			log.error("Signup Error",e);
			userDto.setStatusCode(e.getErrorCodeDescription().getErrorCode());
			userDto.setErrorDescription(e.getErrorCodeDescription().getErrorDescription());
		}  catch (Exception e) {
			log.error("Signup Error",e);
			userDto.setStatusCode(ErrorCodeDescription.ERROR_GENERIC.getErrorCode());
		}        
		log.info("Signup Response : " + userDto);
		return userDto;
	}


}
