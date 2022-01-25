package com.pbx.tabuk.address;

public record Address(Integer id,
                      String houseNameOrNumber,
                      String addressLine1,
                      String addressLine2,
                      String city,
                      String postCode,
                      String country) {
}
