package com.pbx.tabuk.address;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
	void findAllShouldReturnListAndOk() throws Exception {
		when( addressService.getAddresses() ).thenReturn( getAddresses() );

		mockMvc.perform( get( BASE_URL ) )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.length()" ).value( 3 ) );
	}

	@Test
	@DisplayName("When address exits returns 200 and an address")
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
	@DisplayName("When address does not exist throws and 404")
	void findOneShouldThrowAnd404() throws Exception {
		when( addressService.getAddressById( Mockito.any( Long.class ) ) ).thenThrow(
				new AddressNotFoundException( "Account not found." ) );

		mockMvc.perform( get( BASE_URL + "/1" ) )
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath( "$.length()" ).value( 3 ) )
				.andExpect( jsonPath( "$.message" ).value( "Account not found." ))
				.andExpect( jsonPath( "$.description").value( ("uri="+BASE_URL+"/1")));

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