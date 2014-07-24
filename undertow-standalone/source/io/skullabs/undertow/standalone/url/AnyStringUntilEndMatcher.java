package io.skullabs.undertow.standalone.url;

public class AnyStringUntilEndMatcher implements Matcher {

	@Override
	public boolean matches( StringCursor string ) {
		while ( string.hasNext() )
			string.next();
		return true;
	}
}
