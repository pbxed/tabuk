package com.pbx.tabuk.address;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddresses() {
        return addressRepository.selectAddresses();
    }
}
