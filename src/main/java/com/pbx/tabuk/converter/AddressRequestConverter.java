package com.pbx.tabuk.converter;

import com.pbx.tabuk.address.Address;
import com.pbx.tabuk.dto.request.AddressRequest;

public class AddressRequestConverter extends Converter<AddressRequest, Address>{

	public AddressRequestConverter() {
		super( AddressRequestConverter::convertToEntity, AddressRequestConverter::convertToDto);
	}

	private static AddressRequest convertToDto(Address address) {
		AddressRequest addressRequest = new AddressRequest();
		addressRequest.setHouseNameOrNumber( address.getHouseNameOrNumber() );
		addressRequest.setAddressLine1( address.getAddressLine1() );
		addressRequest.setAddressLine2( address.getAddressLine2() );
		addressRequest.setCity( address.getCity() );
		addressRequest.setPostcode( address.getPostcode() );
		addressRequest.setCountry( address.getCountry() );
		return addressRequest;

	}

	private static Address convertToEntity(AddressRequest addressRequest) {
		Address address = new Address();
		address.setHouseNameOrNumber( addressRequest.getHouseNameOrNumber() );
		address.setAddressLine1( addressRequest.getAddressLine1() );
		address.setAddressLine2( addressRequest.getAddressLine2() );
		address.setCity( addressRequest.getCity() );
		address.setPostcode( addressRequest.getPostcode() );
		address.setCountry( addressRequest.getCountry() );
		return address;
	}
}
