package com.pbx.tabuk.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
	@NotBlank(message = "House name or number is required.")
	private String houseNameOrNumber;
	@NotBlank(message = "Address line 1 is required.")
	private String addressLine1;
	private String addressLine2;
	private String city;
	@NotBlank(message = "Postcode is required.")
	private String postcode;
	private String country;
}
