package com.pbx.tabuk.address;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    void shouldReturnOk() throws Exception {
        when(addressService.getAddresses())
                .thenReturn(Arrays.asList(new Address(1, "123", "Fake Street", "Fake", "Fake", "FAKE", "FAKE")));

        this.mockMvc.perform(get("/api/v1/addresses"))
                .andExpect(status().isOk());
    }
}