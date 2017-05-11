package de.friday.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.friday.domain.AddressToken;
import de.friday.domain.CountryFormat;

@Component
public class AddressTokenizer {

	public AddressToken parse(String input) {
		try {
			String[] tokens = input.split(" ");
			CountryFormat countryFormat = guessCountry(tokens);
			AddressParser parser = (AddressParser) countryFormat.getTokenizer().newInstance();
			return parser.parse(input);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private CountryFormat guessCountry(String[] tokens) {
		CountryFormat format = new CountryFormat();

		boolean startsWithNumberAndComma = tokens[0].contains(",");
		boolean startsWithNumberAndNoComma = StringUtils.isNumeric(tokens[0]);
		boolean endsWithNumeric = Character.isDigit(tokens[tokens.length - 1].charAt(0));
		boolean endsWithLetter = Character.isLetter(tokens[tokens.length - 1].charAt(0));

		if (startsWithNumberAndComma) {
			format.setCountry("FR");
			format.setFormat("%d, %s");
		} else if (startsWithNumberAndNoComma) {
			format.setCountry("US");
			format.setFormat("%d %s");
			format.setTokenizer(AddressUSParser.class);
		} else if (endsWithNumeric || endsWithLetter) {
			format.setCountry("DE");
			format.setFormat("%s %d");
			format.setTokenizer(AddressDeutschParser.class);
		}

		return format;
	}

}
