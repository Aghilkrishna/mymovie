package com.app.mymovieserver.exceptions;


/**
 * @author aghil
 *
 */
public enum ErrorCodeDescription {
	UNAUTHORIZED_USER(1000, "Access Denied"),

	PASSWORD_MISMATCH(1001, "Password mismatch"),

	LOGIN_VALIDTE(1002, "Incorrect UserName or Password"),

	ERROR_GENERIC(1003, "Internal Error. Please try again"),

	ERROR_IN_BLOCK_SEATS(1004, "Error in blocking seat. Please try again"),

	ERROR_SERVICE_UNAVAILABLE(1005, "Service unavailable. Please try again"),

	ERROR_SIGNUP_USER_MOBILE_EXIST(1006, "ERROR_SIGNUP_USER_MOBILE_EXIST"),
	ERROR_SIGNUP_USER_EMAIL_EXIST(1007, "ERROR_SIGNUP_USER_EMAIL_EXIST"),
	ERROR_INVALID_PARAMETERS(1008, "ERROR_INVALID_PARAMETERS"),
	ERROR_INVALID_MOVIESCREEN(1009, "ERROR_INVALID_MOVIESCREEN"),
	ERROR_INVALID_USER(1010, "ERROR_INVALID_USER"),
	ERROR_MAX_ALLOWED_SEAT_EXCEEDS(1011, "ERROR_MAX_ALLOWED_SEAT_EXCEEDS"),
	ERROR_INVALID_SEAT(1012, "ERROR_INVALID_SEAT"),
	ERROR_SEAT_NOT_OPEN(1013, "Seat blocked or booked"),
	ERROR_SEAT_BOOKED_BY_OTHER(1014, "ERROR_SEAT_BOOKED_BY_OTHER"),
	ERROR_BOOKING_TIMEOUT(1015, "ERROR_BOOKING_TIMEOUT"),
	ERROR_BOOKING_CANCELLED(1016, "ERROR_BOOKING_CANCELLED"),
	ERROR_INVALID_BOOKING(1017, "ERROR_INVALID_BOOKING");


	private int errorCode;

	private String errorDescription;

	private ErrorCodeDescription(int code, String description) {

		this.errorCode = code;
		this.errorDescription = description;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public static String getDescription(int code) {
		for (ErrorCodeDescription codes : values()) {
			if (codes.getErrorCode() == code)
				return codes.getErrorDescription();
		}
		return null;
	}
}
