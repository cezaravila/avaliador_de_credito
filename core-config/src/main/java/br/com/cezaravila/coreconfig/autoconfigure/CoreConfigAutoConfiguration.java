package br.com.cezaravila.coreconfig.autoconfigure;

import br.com.cezaravila.coreconfig.openapi.OpenApiConfig;
import br.com.cezaravila.coreconfig.security.DevSecurityConfig;
import br.com.cezaravila.coreconfig.security.ProdSecurityConfig;
import br.com.cezaravila.coreconfig.security.ProdSecurityGatewayConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Auto-configuration entry point for core-config.
 * Imported automatically by Spring Boot when core-config is on the classpath.
 */
@AutoConfiguration
@Import({
        OpenApiConfig.class,
        DevSecurityConfig.class,
        ProdSecurityConfig.class,
        ProdSecurityGatewayConfig.class})
public class CoreConfigAutoConfiguration {
}