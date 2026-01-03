package com.example.demo;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class AppController {
    SecretKey signingKey = Keys.hmacShaKeyFor("KodnestSecureSecrete12345678901234567890".getBytes());
	@GetMapping("/get")
	public String addcooktoResponse(@RequestParam String username,@RequestParam("role") String role, HttpServletResponse response) {
		String token = generateToken(username,"role");
		
		Cookie cookie = new Cookie("JWTToken","token");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setDomain("localhost");
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "cokkie addded";
	}

	private String generateToken(String username,String role) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(username);
		builder.claim("role",role);
		builder.setExpiration(new Date(System.currentTimeMillis()+3600000));
		builder.signWith(signingKey);
		String token=builder.compact();
		return token;
		
	}
}
