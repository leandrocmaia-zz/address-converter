package de.friday.domain;

import lombok.Data;

@Data
public class CountryFormat {

	private String country;
	private String format;
	private Class<?> tokenizer;
}
