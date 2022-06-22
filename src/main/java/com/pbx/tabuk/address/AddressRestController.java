package com.pbx.tabuk.address;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbx.tabuk.dto.request.AddressRequest;
import com.pbx.tabuk.dto.response.AddressResponse;

@RestController
@RequestMapping(path = "api/v1/addresses")
public class AddressRestController {

	private final AddressService addressService;

	public AddressRestController( AddressService addressService ) {
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

	@PostMapping
	public ResponseEntity<AddressResponse> createAddress( @Valid @RequestBody AddressRequest addressRequest ) {
		return ResponseEntity.status( HttpStatus.ACCEPTED )
				.body( addressService.createAddress( addressRequest ) );
	}
}
