package com.pbx.tabuk.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {

	private final AddressService addressService;

	public AddressController( final AddressService addressService ) {
		this.addressService = addressService;
	}

	@GetMapping("/address")
	public String address( Model model ) {
		List<String> addresses = new ArrayList<>();

		addressService.getAddresses()
				.forEach( address -> addresses.add( address.getFullFormattedAddress() ) );

		model.addAttribute( "addresses", addresses );
		return "address";
	}
}
