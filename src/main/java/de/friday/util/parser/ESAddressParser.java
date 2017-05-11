package de.friday.util.parser;

import de.friday.domain.AddressToken;
import de.friday.util.AddressParser;

public class ESAddressParser implements AddressParser {
	
	@Override
	public AddressToken parse(String input) {
		String[] tokens = input.split(", ");		
		AddressToken token = new AddressToken();		
		String number = tokens[tokens.length - 1];		
		String rest = input.substring(0, input.indexOf(number)).trim().replace(",","");
		token.setStreetName(rest);
		token.setNumber(number);
		return token;
	}

}
