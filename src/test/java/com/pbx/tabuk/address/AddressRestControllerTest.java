package com.pbx.tabuk.address;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbx.tabuk.dto.request.AddressRequest;
import com.pbx.tabuk.dto.response.AddressResponse;
import com.pbx.tabuk.exception.AddressNotFoundException;

@WebMvcTest(AddressRestController.class)
class AddressRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressService addressService;

	private static String BASE_URL = "/api/v1/addresses";

	@Test
	@DisplayName("Returns 200 and list of addresses")
	@Tag("unit")
	void findAllShouldReturnListAndOk() throws Exception {
		when( addressService.getAddresses() ).thenReturn( getAddresses() );

		mockMvc.perform( get( BASE_URL ) )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.length()" ).value( 3 ) );
	}

	@Test
	@DisplayName("When address exists, returns 200 and an address")
	@Tag("unit")
	void findOneShouldReturnAnAddressAndOk() throws Exception {
		when( addressService.getAddressById( Mockito.any( Long.class ) ) ).thenReturn(
				getAddress() );

		mockMvc.perform( get( BASE_URL + "/1" ) )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.length()" ).value( 7 ) )
				.andExpect( jsonPath( "$.id" ).value( 1 ) )
				.andExpect( jsonPath( "$.houseNameOrNumber" ).value( "123" ) )
				.andExpect( jsonPath( "$.addressLine1" ).value( "addressLine1" ) )
				.andExpect( jsonPath( "$.addressLine2" ).value( "addressLine2" ) )
				.andExpect( jsonPath( "$.city" ).value( "city" ) )
				.andExpect( jsonPath( "$.postcode" ).value( "postcode" ) )
				.andExpect( jsonPath( "$.country" ).value( "country" ) );
	}

	@Test
	@DisplayName("When address does not exist, throws and 404")
	@Tag("unit")
	void findOneShouldThrowAnd404() throws Exception {
		when( addressService.getAddressById( Mockito.any( Long.class ) ) ).thenThrow(
				new AddressNotFoundException( "Account not found." ) );

		mockMvc.perform( get( BASE_URL + "/1" ) )
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath( "$.length()" ).value( 3 ) )
				.andExpect( jsonPath( "$.message" ).value( "Account not found." ) )
				.andExpect( jsonPath( "$.description" ).value( ( "uri=" + BASE_URL + "/1" ) ) );
	}

	@Test
	@DisplayName("When sent valid address request, returns address response and accepted")
	@Tag("unit")
	void createAddressShouldReturnAddressAndAccepted() throws Exception {
		var request = new AddressRequest();
		var response = new AddressResponse();
		var houseNameOrNumber = "test";
		var addressLine1 = "addressLine1";
		var addressLine2 = "addressLine2";
		var city = "city";
		var postcode = "postcode";
		var country = "country";

		request.setHouseNameOrNumber( houseNameOrNumber );
		request.setAddressLine1( addressLine1 );
		request.setAddressLine2( addressLine2 );
		request.setCity( city );
		request.setPostcode( postcode );
		request.setCountry( country );

		response.setId( 1L );
		response.setHouseNameOrNumber( houseNameOrNumber );
		response.setAddressLine1( addressLine1 );
		response.setAddressLine2( addressLine2 );
		response.setCity( city );
		response.setPostcode( postcode );
		response.setCountry( country );

		when( addressService.createAddress( request ) ).thenReturn( response );

		mockMvc.perform( post( BASE_URL )
						.contentType( MediaType.APPLICATION_JSON )
						.content( new ObjectMapper().writeValueAsString( request ) ) )
				.andExpect( status().isAccepted() )
				.andExpect( jsonPath( "$.length()" ).value( 7 ) )
				.andExpect( jsonPath( "$.id" ).value( 1 ) )
				.andExpect( jsonPath( "$.houseNameOrNumber" ).value( houseNameOrNumber ) )
				.andExpect( jsonPath( "$.addressLine1" ).value( addressLine1 ) )
				.andExpect( jsonPath( "$.addressLine2" ).value( addressLine2 ) )
				.andExpect( jsonPath( "$.city" ).value( city ) )
				.andExpect( jsonPath( "$.postcode" ).value( postcode ) )
				.andExpect( jsonPath( "$.country" ).value( country ) );
	}

	@Test
	@DisplayName("When sent invalid house name or number in address request, throws and 400")
	@Tag("unit")
	void createAddressGivenInvalidHouseNameOrNumberFieldInAddressRequestShouldThrowAnd400Response() throws Exception {
		var request = new AddressRequest();
		var response = new AddressResponse();
		var houseNameOrNumber = "  ";
		var addressLine1 = "addressLine1";
		var addressLine2 = "addressLine2";
		var city = "city";
		var postcode = "postcode";
		var country = "country";

		request.setHouseNameOrNumber( houseNameOrNumber );
		request.setAddressLine1( addressLine1 );
		request.setAddressLine2( addressLine2 );
		request.setCity( city );
		request.setPostcode( postcode );
		request.setCountry( country );

		response.setId( 1L );
		response.setHouseNameOrNumber( houseNameOrNumber );
		response.setAddressLine1( addressLine1 );
		response.setAddressLine2( addressLine2 );
		response.setCity( city );
		response.setPostcode( postcode );
		response.setCountry( country );

		when( addressService.createAddress( request ) ).thenReturn( response );

		mockMvc.perform( post( BASE_URL )
						.contentType( MediaType.APPLICATION_JSON )
						.content( new ObjectMapper().writeValueAsString( request ) ) )
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath( "$.length()" ).value( 3 ) )
				.andExpect( jsonPath( "$.message" ).value( "House name or number is required." ) )
				.andExpect( jsonPath( "$.description" ).value( "uri=" + BASE_URL ) );
	}

	@Test
	@DisplayName("When sent invalid address line 1 in address request, throws and 400")
	@Tag("unit")
	void createAddressGivenInvalidAddressLine1FieldInAddressRequestShouldThrowAnd400Response() throws Exception {
		var request = new AddressRequest();
		var response = new AddressResponse();
		var houseNameOrNumber = "houseNameOrNumber";
		var addressLine1 = "   ";
		var addressLine2 = "addressLine2";
		var city = "city";
		var postcode = "postcode";
		var country = "country";

		request.setHouseNameOrNumber( houseNameOrNumber );
		request.setAddressLine1( addressLine1 );
		request.setAddressLine2( addressLine2 );
		request.setCity( city );
		request.setPostcode( postcode );
		request.setCountry( country );

		response.setId( 1L );
		response.setHouseNameOrNumber( houseNameOrNumber );
		response.setAddressLine1( addressLine1 );
		response.setAddressLine2( addressLine2 );
		response.setCity( city );
		response.setPostcode( postcode );
		response.setCountry( country );

		when( addressService.createAddress( request ) ).thenReturn( response );

		mockMvc.perform( post( BASE_URL )
						.contentType( MediaType.APPLICATION_JSON )
						.content( new ObjectMapper().writeValueAsString( request ) ) )
				.andExpect( status().isBadRequest() )
				.andExpect( jsonPath( "$.length()" ).value( 3 ) )
				.andExpect( jsonPath( "$.message" ).value( "Address line 1 is required." ) )
				.andExpect( jsonPath( "$.description" ).value( "uri=" + BASE_URL ) );
	}

	private Address getAddress() {
		return new Address( 1L, "123", "addressLine1", "addressLine2", "city", "postcode",
				"country" );
	}

	private List<Address> getAddresses() {
		var addressOne = new Address( 1L, "123", "addressLine1", "addressLine2", "city", "postcode",
				"country" );
		var addressTwo = new Address( 2L, "456", "addressLine1", "addressLine2", "city", "postcode",
				"country" );
		var addressThree = new Address( 3L, "987", "addressLine1", "addressLine2", "city",
				"postcode", "country" );

		return Arrays.asList( addressOne, addressTwo, addressThree );
	}
}