package de.friday.util;

import de.friday.domain.AddressToken;

public interface AddressParser {

	public AddressToken parse(String input);
}
