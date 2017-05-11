package de.friday.util;

import de.friday.domain.AddressToken;

public class AddressDeutschParser implements AddressParser {
	
	@Override
	public AddressToken parse(String input) {
		String[] tokens = input.split(" ");
		
		AddressToken token = new AddressToken();
		
		// checks if last token starts with a numeric value		
		String lastToken = tokens[tokens.length -1];
		 
		if (Character.isDigit(lastToken.charAt(0))) {
			String rest = input.substring(0,input.indexOf(lastToken)).trim();
			token.setStreetName(rest);
			token.setNumber(lastToken);
		} else if (Character.isLetter(lastToken.charAt(0))) { // in case 23 b
			String number = tokens[tokens.length - 2] + " " + tokens[tokens.length - 1];
			String rest = input.substring(0,input.indexOf(number)).trim();
			token.setStreetName(rest);
			token.setNumber(number);
		} else { 
			token.setStreetName(tokens[0]);
			token.setNumber(tokens[1]);
		}
		
		return token;
	}

}
