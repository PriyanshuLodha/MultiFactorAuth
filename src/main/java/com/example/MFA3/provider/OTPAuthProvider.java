package com.example.MFA3.provider;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.MFA3.auth.OTPAuthToken;
import com.example.MFA3.auth.UserPasswordAuthToken;
import com.example.MFA3.repo.UserSecretKeyRepo;
import com.example.MFA3.service.MyUserDetailsService;

@Component
public class OTPAuthProvider implements AuthenticationProvider {

	@Autowired UserSecretKeyRepo secretKeyRepo;
	
	@Override
	public Authentication authenticate(Authentication auth) {
		var user = secretKeyRepo.findByUsername(auth.getName());

		if (user.isPresent()) {
			return new OTPAuthToken(auth.getName(),
					auth.getCredentials() ,
					Arrays.asList(() -> "read"));
		}

		throw new BadCredentialsException("Failed Scret-key Authentication ??");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OTPAuthToken.class.equals(authentication);
	}
}
