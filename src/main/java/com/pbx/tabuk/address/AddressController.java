package com.pbx.tabuk.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pbx.tabuk.dto.request.AddressRequest;

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
				.forEach( address -> addresses.add( address.toFullFormattedAddress() ) );

		model.addAttribute( "addresses", addresses );
		model.addAttribute( "addressRequest", new AddressRequest(  ) );

		return "address";
	}

	@PostMapping("/address")
	public String createAddress( @ModelAttribute AddressRequest request ) {
		addressService.createAddress(request);
		return "redirect:/address/";
	}
}
