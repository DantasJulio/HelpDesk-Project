package com.julio.helpdesk.resources.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.julio.helpdesk.domain.Usuario;
import com.julio.helpdesk.domain.dto.Login;
import com.julio.helpdesk.services.TokenService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	private Authentication authenticate;
	private TokenService tokenService;
	
	@PostMapping("/login")
	public String login(@RequestBody Login login ) {
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
		
		Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
		
		Usuario user = (Usuario) authenticate.getPrincipal();
		
		return tokenService.gerarToken(user);
		
	}
	

}
