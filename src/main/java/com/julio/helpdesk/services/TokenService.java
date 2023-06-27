package com.julio.helpdesk.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.julio.helpdesk.domain.Usuario;

@Service
public class TokenService {

	public String gerarToken(Usuario user) {
		return JWT.create()
				.withIssuer("Pessoas")
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(10)
						.toInstant(ZoneOffset.of("-03:00"))
				).sign(Algorithm.HMAC256("testando"));
						
	}
}
