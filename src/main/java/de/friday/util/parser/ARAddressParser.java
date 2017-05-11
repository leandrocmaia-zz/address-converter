package de.friday.util.parser;

import de.friday.domain.AddressToken;
import de.friday.util.AddressParser;

public class ARAddressParser implements AddressParser {
	
	@Override
	public AddressToken parse(String input) {
		String[] tokens = input.split(" No ");		
		AddressToken token = new AddressToken();		
		String number = tokens[tokens.length - 1].trim();		
		String rest = input.substring(0, input.indexOf(number)).trim().replace("No","");
		token.setStreetName(rest.trim());
		token.setNumber("No " + number);
		return token;
	}

}
