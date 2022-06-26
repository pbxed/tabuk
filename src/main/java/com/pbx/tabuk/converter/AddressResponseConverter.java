package com.pbx.tabuk.converter;

import com.pbx.tabuk.address.Address;
import com.pbx.tabuk.dto.response.AddressResponse;

public class AddressResponseConverter extends Converter<AddressResponse, Address> {

	public AddressResponseConverter() {
		super( AddressResponseConverter::convertToEntity, AddressResponseConverter::convertToDto);
	}

	private static AddressResponse convertToDto(Address address) {
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setId( address.getId() );
		addressResponse.setHouseNameOrNumber( address.getHouseNameOrNumber() );
		addressResponse.setAddressLine1( address.getAddressLine1() );
		addressResponse.setAddressLine2( address.getAddressLine2() );
		addressResponse.setCity( address.getCity() );
		addressResponse.setPostcode( address.getPostcode() );
		addressResponse.setCountry( address.getCountry() );
		return addressResponse;

	}

	private static Address convertToEntity(AddressResponse addressResponse) {
		Address address = new Address();
		address.setHouseNameOrNumber( addressResponse.getHouseNameOrNumber() );
		address.setAddressLine1( addressResponse.getAddressLine1() );
		address.setAddressLine2( addressResponse.getAddressLine2() );
		address.setCity( addressResponse.getCity() );
		address.setPostcode( addressResponse.getPostcode() );
		address.setCountry( addressResponse.getCountry() );
		return address;
	}

}
