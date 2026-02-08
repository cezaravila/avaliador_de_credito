package br.com.cezaravila.coreconfig.security;

public final class SecurityPaths {

    private SecurityPaths() {}

    public static final String[] SWAGGER_PUBLIC = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    public static final String[] ACTUATOR_PUBLIC = {
            "/actuator/**"
    };
}