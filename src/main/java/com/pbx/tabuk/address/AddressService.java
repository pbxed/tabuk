package com.pbx.tabuk.address;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pbx.tabuk.exception.AddressNotFoundException;

@Service
public class AddressService {

	private final AddressRepository addressRepository;

	public AddressService( AddressRepository addressRepository ) {
		this.addressRepository = addressRepository;
	}

	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}

	public Address getAddressById( final Long id ) {
		return addressRepository.findById( id )
				.orElseThrow( () -> new AddressNotFoundException(
						"Account with id: " + id + " not found." ) );
	}
}
