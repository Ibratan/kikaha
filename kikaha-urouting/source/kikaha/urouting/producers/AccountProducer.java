package kikaha.urouting.producers;

import io.undertow.security.idm.Account;
import io.undertow.server.HttpServerExchange;
import kikaha.urouting.api.ContextProducer;
import kikaha.urouting.api.RoutingException;
import lombok.val;
import trip.spi.Singleton;

@Singleton( exposedAs = ContextProducer.class )
public class AccountProducer implements ContextProducer<Account> {

	@Override
	public Account produce( final HttpServerExchange exchange ) throws RoutingException {
		val securityContext = exchange.getSecurityContext();
		if ( securityContext == null )
			return null;
		return securityContext.getAuthenticatedAccount();
	}
}