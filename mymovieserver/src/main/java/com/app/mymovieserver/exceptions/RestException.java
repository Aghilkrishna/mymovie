package com.app.mymovieserver.exceptions;


/**
 * @author aghil
 *
 */
public class RestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ErrorCodeDescription errorCodeDescription;

	private String message;

	public RestException() {

	}

	public RestException(ErrorCodeDescription errorCodeDescription) {
		this.errorCodeDescription = errorCodeDescription;
	}

	public RestException(String message) {
		super(message);
	}

	public RestException(ErrorCodeDescription exceptionCode, String message) {
		this.errorCodeDescription = exceptionCode;
		this.message = message;
	}

	public ErrorCodeDescription getErrorCodeDescription() {
		return errorCodeDescription;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "RestException [errorCodeDescription=" + errorCodeDescription + ", message=" + message + "]";
	}

}
