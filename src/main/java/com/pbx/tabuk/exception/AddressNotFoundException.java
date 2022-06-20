package com.pbx.tabuk.exception;

public class AddressNotFoundException extends RuntimeException {
	public AddressNotFoundException( final String message ) {
		super( message );
	}
}
