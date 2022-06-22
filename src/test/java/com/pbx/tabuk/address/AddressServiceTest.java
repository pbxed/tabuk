package com.pbx.tabuk.address;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pbx.tabuk.exception.AddressNotFoundException;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@Mock
	private AddressRepository addressRepository;

	@InjectMocks
	private AddressService addressService;

	@Test
	@DisplayName("Returns a list of Addresses successfully")
	@Tag("unit")
	void getAddressesShouldReturnListOfAddresses() {
		// Given
		var expected = getAddresses();

		when( addressRepository.findAll() ).thenReturn( expected );

		// When
		final List<Address> actual = addressService.getAddresses();

		// Then
		assertThat( actual ).isEqualTo( expected );
	}

	@Test
	@DisplayName("Returns an Address when given a valid Id")
	@Tag("unit")
	void getAddressByIdGivenValidIdShouldReturnAddress() {
		var expected = getAddress();

		when( addressRepository.findById( 1L ) ).thenReturn( Optional.of( expected ) );

		final Address actual = addressService.getAddressById( 1L );

		assertThat( actual ).isEqualTo( expected );

	}

	@Test
	@DisplayName("Throws AccountNotFoundException when id does not exist")
	@Tag("unit")
	void getAddressByIdWhenIdDoesNotExistShouldThrow() {
		var expected = getAddress();

		when( addressRepository.findById( 1L ) ).thenReturn( Optional.empty() );

		assertThrows( AddressNotFoundException.class, () -> {
			final Address actual = addressService.getAddressById( 1L );
		} );
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