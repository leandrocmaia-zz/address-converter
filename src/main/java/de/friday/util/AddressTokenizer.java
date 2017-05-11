package de.friday.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.friday.domain.AddressToken;
import de.friday.domain.CountryFormat;
import de.friday.util.parser.ARAddressParser;
import de.friday.util.parser.DEAddressParser;
import de.friday.util.parser.ESAddressParser;
import de.friday.util.parser.FRAddressParser;
import de.friday.util.parser.USAddressParser;

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
		boolean streetNameEndsWithComma = tokens[tokens.length - 2].trim().contains(",");
		boolean numberHasPrefix = tokens[tokens.length - 2].trim().equals("No");

		if (startsWithNumberAndComma) {
			format.setCountry("FR");
			format.setTokenizer(FRAddressParser.class);
		} else if (startsWithNumberAndNoComma) {
			format.setCountry("US");
			format.setTokenizer(USAddressParser.class);
		} else if ((endsWithNumeric && !streetNameEndsWithComma && !numberHasPrefix) || endsWithLetter) {
			format.setCountry("DE");
			format.setTokenizer(DEAddressParser.class);
		} else if (streetNameEndsWithComma) {
			format.setCountry("ES");
			format.setTokenizer(ESAddressParser.class);
		} else if (numberHasPrefix) {
			format.setCountry("AR");
			format.setTokenizer(ARAddressParser.class);
		}

		return format;
	}

}
