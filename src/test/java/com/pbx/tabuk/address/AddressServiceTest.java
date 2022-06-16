package com.pbx.tabuk.address;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@Mock
	private AddressRepository addressRepository;

	@InjectMocks
	private AddressService cut;

	@Test
	@DisplayName("Returns a list of Addresses successfully")
	void happyPath() {
		// Given
		var expected = Arrays.asList(
				new Address( 1L, "123", "AddressLine1", "AddressLine2", "City", "PostCode",
						"Country" ),
				new Address( 2L, "123", "AddressLine1", "AddressLine2", "City", "PostCode",
						"Country" ) );

		when( addressRepository.findAll() ).thenReturn( expected );

		// When
		final List<Address> actual = cut.getAddresses();

		// Then
		assertThat(actual).isEqualTo( expected );
	}

}