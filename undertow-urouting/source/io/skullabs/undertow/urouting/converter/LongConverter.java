package io.skullabs.undertow.urouting.converter;



public class LongConverter extends AbstractConverter<Long> {

	@Override
	public Long convert(String value) throws ConversionException {
		return Long.valueOf( value );
	}

}
