package com.pbx.tabuk.address;

import static com.pbx.tabuk.util.StringNullChecker.getNonNullStringValue;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "house_name_or_number")
	private String houseNameOrNumber;
	@Column(name = "address_line_1")
	private String addressLine1;
	@Column(name = "address_line_2")
	private String addressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "postcode")
	private String postcode;
	@Column(name = "country")
	private String country;

	public String toFullFormattedAddress() {
		StringJoiner result = new StringJoiner( "", "", " ");

		return result
				.add( getNonNullStringValue( houseNameOrNumber ) )
				.add( getNonNullStringValue( addressLine1 ) )
				.add( getNonNullStringValue( addressLine2 ) )
				.add( getNonNullStringValue( city ) )
				.add( getNonNullStringValue( postcode ) )
				.add( getNonNullStringValue( country ) )
				.toString()
				.trim();
	}
}
