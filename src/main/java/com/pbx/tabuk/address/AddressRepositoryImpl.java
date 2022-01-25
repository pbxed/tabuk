package com.pbx.tabuk.address;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public List<Address> selectAddresses() {
        throw new UnsupportedOperationException("not implemented");
    }
}
