package com.pbx.tabuk.address;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

	private final AddressService addressService;

	public AddressController( final AddressService addressService ) {
		this.addressService = addressService;
	}

	@GetMapping("/address")
	public String address( Model model) {
		model.addAttribute( "addresses", addressService.getAddresses() );
		return "address";
	}
}
