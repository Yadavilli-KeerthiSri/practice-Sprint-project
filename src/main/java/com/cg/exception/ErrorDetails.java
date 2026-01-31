package com.cg.exception;

import java.util.Date;

public class ErrorDetails {
	private String message;
	private Date timestamp;
	private String detail;
	
	public ErrorDetails(String message, Date timestamp, String detail) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.detail = detail;
	}

	public ErrorDetails() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetail() {
		return detail;
	}
	
	
	
	

}
