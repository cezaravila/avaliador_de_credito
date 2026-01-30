package br.com.cezaravila.coreconfig.autoconfigure;

import br.com.cezaravila.coreconfig.openapi.OpenApiConfig;
import br.com.cezaravila.coreconfig.security.DevSecurityConfig;
import br.com.cezaravila.coreconfig.security.ProdSecurityConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Auto-configuration entry point for core-config.
 * Imported automatically by Spring Boot when core-config is on the classpath.
 */
@AutoConfiguration
@Import({OpenApiConfig.class, DevSecurityConfig.class, ProdSecurityConfig.class})
public class CoreConfigAutoConfiguration {
}