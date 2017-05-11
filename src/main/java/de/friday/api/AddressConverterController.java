package de.friday.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.friday.domain.AddressToken;
import de.friday.util.AddressTokenizer;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AddressConverterController {
	
	private AddressTokenizer addressTokenizer;
	
	@Autowired
	public AddressConverterController(AddressTokenizer addressTokenizer) {
		this.addressTokenizer = addressTokenizer;
	}

	@GetMapping(value = "/convert")
    public ResponseEntity<?> convertAddress(@RequestParam("address") String fullAddress)  {
		log.info("Converting address {}", fullAddress);
		AddressToken response = addressTokenizer.parse(fullAddress);
        return ResponseEntity.ok(response);
    }
	
}