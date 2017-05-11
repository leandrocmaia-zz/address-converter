package de.friday.util;

import de.friday.domain.AddressToken;

public class AddressUSParser implements AddressParser {
	
	@Override
	public AddressToken parse(String input) {
		String[] tokens = input.split(" ");		
		AddressToken token = new AddressToken();		
		String firstToken = tokens[0];		
		String rest = input.substring(firstToken.length(), input.length()).trim();
		token.setStreetName(rest);
		token.setNumber(firstToken);
		return token;
	}

}
