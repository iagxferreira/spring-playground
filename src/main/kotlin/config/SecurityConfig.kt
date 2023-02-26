package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }
            .authorizeRequests(
                Customizer { auth ->
                    auth
                        .anyRequest().authenticated()
                }
            )
            .oauth2ResourceServer { obj: OAuth2ResourceServerConfigurer<HttpSecurity?> -> obj.jwt() }
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .httpBasic(withDefaults())
            .build()
    }


    @Bean
    fun users(): InMemoryUserDetailsManager =
        InMemoryUserDetailsManager(
            User.withUsername("dvega")
                .password("password")
                .authorities("read")
                .build()
        )
}