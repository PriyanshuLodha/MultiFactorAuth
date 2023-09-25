package com.example.MFA3.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.example.MFA3.model.Test;
import com.example.MFA3.repo.TestRepo;
import com.example.MFA3.service.TwoFactorAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.MFA3.auth.OTPAuthToken;
import com.example.MFA3.auth.UserPasswordAuthToken;
import com.example.MFA3.model.UserSecretKey;
import com.example.MFA3.repo.UserSecretKeyRepo;


@Component
public class UserPasswordAuthFilter extends OncePerRequestFilter {
	
	@Autowired AuthenticationManager manager;
	
	@Autowired
	UserSecretKeyRepo userSecretKeyRepo;
	@Autowired
	TwoFactorAuthService twoFactorAuthService;

	@Autowired
	TestRepo repo;

	@Modifying
	@Transactional
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		var uname = request.getHeader("uname");
		var password = request.getHeader("password");
		
		var key = request.getHeader("secret-key");
		
		if(key == null) {
			//uid and password
			var a = new UserPasswordAuthToken(uname,password);
			var auth = manager.authenticate(a);
			
			
			
			//save generated key into db
			UserSecretKey secretKey = new UserSecretKey();
			var secret=twoFactorAuthService.generateNewSecret();

			secretKey.setKey(secret);
			secretKey.setUsername(uname);
			userSecretKeyRepo.save(secretKey);
//			Test t = new Test();
//			t.setUsername("test");
//			repo.save(t);
		}
		else {
			// through the key 
			var auth = manager.authenticate(new OTPAuthToken(uname,password));
			Optional<Test> user=repo.findByUsername(uname);
			if(user.isEmpty()){
				return ;
			}

			}
			// generate a token



	}

	@Override
    protected boolean shouldNotFilter(HttpServletRequest req)
    		throws ServletException {
        return !req.getServletPath().equals("/hello");
    }
}