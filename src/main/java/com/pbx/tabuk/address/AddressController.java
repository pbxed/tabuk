package com.pbx.tabuk.address;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/addresses")
public class AddressController {

	private final AddressService addressService;

	public AddressController( AddressService addressService ) {
		this.addressService = addressService;
	}

	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		return ResponseEntity.status( HttpStatus.OK ).body( addressService.getAddresses() );
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> findOne( @PathVariable Long id ) {
		return ResponseEntity.status( HttpStatus.OK ).body( addressService.getAddressById( id ) );
	}
}
