package com.pbx.tabuk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
	private Long id;
	private String houseNameOrNumber;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postcode;
	private String country;
}
