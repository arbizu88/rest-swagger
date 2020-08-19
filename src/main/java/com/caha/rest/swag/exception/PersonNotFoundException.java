package com.caha.rest.swag.exception;

public class PersonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1900913391210868493L;

	public PersonNotFoundException(Long id) {
		super("Could not find person " + id);
	}
}
