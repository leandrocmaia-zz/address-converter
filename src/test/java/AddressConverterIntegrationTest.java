import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.friday.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AddressConverterIntegrationTest.class, Application.class})
@EnableConfigurationProperties
public class AddressConverterIntegrationTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
	@Test
	public void testSimpleAddressLine() throws Exception {
		String input = "Winterallee 3";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Winterallee")))
	            .andExpect(jsonPath("$.number", is("3")));
		
	}
	
	@Test
	public void testSimpleAddressLine2() throws Exception {
		String input = "Blaufeldweg 123B";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Blaufeldweg")))
	            .andExpect(jsonPath("$.number", is("123B")));
	}
	
	@Test
	public void testSimpleAddressLine3() throws Exception {
		String input = "Am Bächle 23";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Am Bächle")))
	            .andExpect(jsonPath("$.number", is("23")));
	}
	
	
	@Test
	public void testSimpleAddressLine4() throws Exception {
		String input = "Auf der Vogelwiese 23 b";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Auf der Vogelwiese")))
	            .andExpect(jsonPath("$.number", is("23 b")));
	}
	
	@Test
	public void testSimpleAddressLine5() throws Exception {
		String input = "Auf der Vogelwiese 1";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Auf der Vogelwiese")))
	            .andExpect(jsonPath("$.number", is("1")));
	}
	
	@Test
	public void testSimpleAddressLineUS() throws Exception {
		String input = "200 Broadway Av";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Broadway Av")))
	            .andExpect(jsonPath("$.number", is("200")));
	}
	
	@Test
	public void testSimpleAddressLineFR() throws Exception {
		String input = "4, rue de la revolution";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("rue de la revolution")))
	            .andExpect(jsonPath("$.number", is("4")));
	}
	
	@Test
	public void testSimpleAddressLineES() throws Exception {
		String input = "Calle Aduana, 29";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Calle Aduana")))
	            .andExpect(jsonPath("$.number", is("29")));
	}
	
	@Test
	public void testSimpleAddressLineAR() throws Exception {
		String input = "Calle 39 No 1540";
		mockMvc.perform(get("/convert")
	            .param("address", input))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.streetName", is("Calle 39")))
	            .andExpect(jsonPath("$.number", is("No 1540")));
	}
	
}
