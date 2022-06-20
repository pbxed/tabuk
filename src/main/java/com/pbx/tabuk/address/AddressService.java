package com.pbx.tabuk.address;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pbx.tabuk.dto.request.AddressRequest;
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

	public void createAddress( final AddressRequest request ) {
		Address address = mapAddressRequestToAddress(request);

		addressRepository.save( address );
	}

	private Address mapAddressRequestToAddress( final AddressRequest request ) {
		Address address = new Address();
		address.setHouseNameOrNumber( request.getHouseNameOrNumber() );
		address.setAddressLine1( request.getAddressLine1() );
		address.setAddressLine2( request.getAddressLine2() );
		address.setCity( request.getCity() );
		address.setPostcode( request.getPostcode() );
		address.setCountry( request.getCountry() );

		return address;
	}
}
