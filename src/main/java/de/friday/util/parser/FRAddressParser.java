package de.friday.util.parser;

import de.friday.domain.AddressToken;
import de.friday.util.AddressParser;

public class FRAddressParser implements AddressParser {
	
	@Override
	public AddressToken parse(String input) {
		String[] tokens = input.split(", ");		
		AddressToken token = new AddressToken();
		token.setStreetName(tokens[1]);
		token.setNumber(tokens[0]);
		return token;
	}

}
