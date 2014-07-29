package io.skullabs.undertow.urouting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import io.skullabs.undertow.urouting.User.Address;
import io.skullabs.undertow.urouting.api.Mimes;
import io.skullabs.undertow.urouting.api.Serializer;
import io.skullabs.undertow.urouting.api.Unserializer;

import java.io.StringReader;
import java.io.StringWriter;

import lombok.SneakyThrows;

import org.junit.Test;

import trip.spi.ServiceProvider;

public class SerializationTests extends TestCase {

	final ServiceProvider provider = new ServiceProvider();
	final User user = new User( "gerolasdiwn",
			new Address( "Madison Avenue", 10 ) );

	@Test
	@SneakyThrows
	public void grantThatSerializeItAsXML() {
		Serializer serializer = provider.load( Serializer.class, Mimes.XML );
		StringWriter output = new StringWriter();
		serializer.serialize( user, output );
		String expected = readFile( "serialization.expected-xml.xml" );
		assertThat( output.toString(), is( expected ) );
	}

	@Test
	@SneakyThrows
	public void grantThatUnserializeXMLIntoObjectAsExpected() {
		String xml = readFile( "serialization.expected-xml.xml" );
		Unserializer unserializer = provider.load( Unserializer.class, Mimes.XML );
		User user = unserializer.unserialize( new StringReader( xml ), User.class );
		assertIsValidUser( user );
	}

	@Test
	@SneakyThrows
	public void grantThatSerializeItAsJSON() {
		Serializer serializer = provider.load( Serializer.class, Mimes.JSON );
		StringWriter output = new StringWriter();
		serializer.serialize( user, output );
		String expected = readFile( "serialization.expected-json.json" );
		assertThat( output.toString(), is( expected ) );
	}

	@Test
	@SneakyThrows
	public void grantThatUnserializeJSONIntoObjectAsExpected() {
		String json = readFile( "serialization.expected-json.json" );
		Unserializer unserializer = provider.load( Unserializer.class, Mimes.JSON );
		User user = unserializer.unserialize( new StringReader( json ), User.class );
		assertIsValidUser( user );
	}

	void assertIsValidUser( User user ) {
		assertNotNull( user );
		assertThat( user.name, is( "gerolasdiwn" ) );
		assertNotNull( user.addresses );
		assertThat( user.addresses.size(), is( 1 ) );
		Address address = user.addresses.get( 0 );
		assertThat( address.street, is( "Madison Avenue" ) );
		assertThat( address.number, is( 10 ) );
	}
}