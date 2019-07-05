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

import br.com.gbessa.sovina.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	@Autowired 
	private WebApplicationContext context;
    
	@MockBean 
    private ProductRepository productRepository;
    
    private MockMvc mockMvc;
//    private MockHttpSession mockSession;

    private String contentType;
    
    @Before
    public void setup() {
    	contentType = "application/json;charset=UTF-8";
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        //this.mockSession = new MockHttpSession(context.getServletContext(), UUID.randomUUID().toString());
        //this.mockSession.setAttribute("loggedUser", buildLoggedUser());
    }

    @Test
    public void testListAllProducts() throws Exception {
		this.mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().json("[]"));
    }
	
}
