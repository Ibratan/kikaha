package io.skullabs.undertow.standalone.auth;

import io.skullabs.undertow.standalone.api.AuthenticationConfiguration;
import io.skullabs.undertow.standalone.api.AuthenticationRuleConfiguration;
import io.undertow.security.api.AuthenticationMechanism;
import io.undertow.security.idm.IdentityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.val;
import lombok.experimental.Accessors;

@Getter
@Accessors( fluent = true )
@SuppressWarnings( "rawtypes" )
public class AuthenticationRuleMatcher {

	final Map<String, AuthenticationMechanism> mechanisms;
	final Map<String, IdentityManager> identityManagers;
	final List<AuthenticationRule> rules;
	final AuthenticationConfiguration authConfig;

	public AuthenticationRuleMatcher( AuthenticationConfiguration authConfig ) {
		this.authConfig = authConfig;
		mechanisms = instantiateMechanismsFoundOnConfig();
		identityManagers = instantiateIdentityManagersFoundOnConfig();
		rules = readRulesFromConfig();
	}

	Map<String, AuthenticationMechanism> instantiateMechanismsFoundOnConfig() {
		val mechanisms = new HashMap<String, AuthenticationMechanism>();
		for ( String id : authConfig.mechanisms().keySet() ) {
			val originalClass = authConfig.mechanisms().get( id );
			mechanisms.put( id, (AuthenticationMechanism)instantiate( originalClass ) );
		}
		return mechanisms;
	}

	Map<String, IdentityManager> instantiateIdentityManagersFoundOnConfig() {
		val identityManagers = new HashMap<String, IdentityManager>();
		for ( String id : authConfig.identityManagers().keySet() ) {
			val originalClass = authConfig.identityManagers().get( id );
			identityManagers.put( id, (IdentityManager)instantiate( originalClass ) );
		}
		return identityManagers;
	}

	Object instantiate( Class clazz ) {
		try {
			return clazz.newInstance();
		} catch ( InstantiationException | IllegalAccessException e ) {
			throw new IllegalStateException( e );
		}
	}

	List<AuthenticationRule> readRulesFromConfig() {
		val rules = new ArrayList<AuthenticationRule>();
		for ( val ruleConf : authConfig.authenticationRules() )
			rules.add( convertConfToRule( ruleConf ) );
		return rules;
	}

	AuthenticationRule convertConfToRule( final AuthenticationRuleConfiguration ruleConf ) {
		val identityManager = identityManagers().get( ruleConf.identityManager() );
		val mechanisms = extractNeededMechanisms( ruleConf );
		return new AuthenticationRule(
				ruleConf.pattern(), identityManager,
				mechanisms, ruleConf.expectedRoles(), null );
	}

	List<AuthenticationMechanism> extractNeededMechanisms(
			final AuthenticationRuleConfiguration ruleConf ) {
		val mechanisms = new ArrayList<AuthenticationMechanism>();
		for ( val mechanism : ruleConf.mechanisms() )
			mechanisms.add( mechanisms().get( mechanism ) );
		return mechanisms;
	}

	AuthenticationRule retrieveAuthenticationRuleForUrl( String url ) {
		for ( val rule : rules )
			if ( rule.matches( url ) )
				return rule;
		return null;
	}
}
