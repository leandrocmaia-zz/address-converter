import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.friday.domain.AddressToken;
import de.friday.util.AddressTokenizer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AddressTokenizerTest.class})
public class AddressTokenizerTest {

	private AddressTokenizer addressTokenizer;
	
	@Before
	public void before() {
		addressTokenizer = new AddressTokenizer();		
	}
	
	@Test
	public void testSimpleAddressLine() {
		String input = "Winterallee 3";
		AddressToken token = addressTokenizer.parse(input);
		assertEquals(token.getNumber(), "3");
		assertEquals(token.getStreetName(), "Winterallee");
	}
	
	@Test
	public void testSimpleAddressLine2() {
		String input = "Blaufeldweg 123B";
		AddressToken token = addressTokenizer.parse(input);
		assertEquals(token.getNumber(), "123B");
		assertEquals(token.getStreetName(), "Blaufeldweg");
	}
	
	@Test
	public void testSimpleAddressLine3() {
		String input = "Am Bächle 23";
		AddressToken token = addressTokenizer.parse(input);
		assertEquals(token.getNumber(), "23");
		assertEquals(token.getStreetName(), "Am Bächle");
	}
	
	
	@Test
	public void testSimpleAddressLine4() {
		String input = "Auf der Vogelwiese 23 b";
		AddressToken token = addressTokenizer.parse(input);
		assertEquals(token.getNumber(), "23 b");
		assertEquals(token.getStreetName(), "Auf der Vogelwiese");
	}
	
	@Test
	public void testSimpleAddressLine5() {
		String input = "Auf der Vogelwiese 1";
		AddressToken token = addressTokenizer.parse(input);
		assertEquals(token.getNumber(), "1");
		assertEquals(token.getStreetName(), "Auf der Vogelwiese");
	}
	
	@Test
	public void testSimpleAddressLine6() {
		String input = "200 Broadway Av";
		AddressToken token = addressTokenizer.parse(input);
		assertEquals(token.getNumber(), "200");
		assertEquals(token.getStreetName(), "Broadway Av");
	}
	
	
}
