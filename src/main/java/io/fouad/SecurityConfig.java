package io.fouad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Value("${app.jwt.signature.public-key}")
	private String jwtSignaturePublicKeyBase64;

	private final OAuth2AuthEntryPoint oAuth2AuthEntryPoint;

	public SecurityConfig(OAuth2AuthEntryPoint oAuth2AuthEntryPoint) {
		this.oAuth2AuthEntryPoint = oAuth2AuthEntryPoint;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .httpBasic(HttpBasicConfigurer::disable)
		    .formLogin(FormLoginConfigurer::disable)
		    .logout(LogoutConfigurer::disable)
		    .csrf(CsrfConfigurer::disable)
		    .cors(Customizer.withDefaults())
		    .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.oauth2ResourceServer(o -> o.authenticationEntryPoint(oAuth2AuthEntryPoint)
			.jwt(Customizer.withDefaults()));
		return http.build();
	}

	@Bean
	public JwtDecoder jwtDecoder() throws Exception {
		byte[] jwtSignaturePublicKeyBytes = Base64.getDecoder().decode(jwtSignaturePublicKeyBase64);
		var keyFactory = KeyFactory.getInstance("RSA");
		var publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(jwtSignaturePublicKeyBytes));
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
	}
}