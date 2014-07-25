package io.skullabs.undertow.standalone.api;

import java.util.List;
import java.util.Map;

@SuppressWarnings( "rawtypes" )
public interface AuthenticationConfiguration {

	Map<String, Class> mechanisms();

	Map<String, Class> identityManagers();

	AuthenticationRuleConfiguration defaultRule();

	List<AuthenticationRuleConfiguration> authenticationRules();
}
