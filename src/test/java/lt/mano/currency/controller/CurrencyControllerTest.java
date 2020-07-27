package lt.mano.currency.controller;

import lt.mano.currency.repository.RateRepository;

import lt.mano.currency.services.XMLService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CurrencyControllerTest {

    private MockMvc mockMvc;

    @Mock
    XMLService xmlService;

    @Mock
    RateRepository rateRepository;

    @InjectMocks
    CurrencyController currencyController;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(currencyController)
                .build();
    }

    @Test
    public void testIndexMockMVC() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}

