package br.com.gbessa.sovina.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.gbessa.sovina.repositories.PriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceControllerTest {

	@Autowired 
	private WebApplicationContext context;

	@MockBean 
    private PriceRepository priceRepository;
	
	private MockMvc mockMvc;
    private String contentType;
    
    @Before
    public void setup() {
    	contentType = "application/json;charset=UTF-8";
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void testListPrices() throws Exception {
		this.mockMvc.perform(get("/prices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().json("[]"));
    }
    
}
