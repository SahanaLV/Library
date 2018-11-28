package com.sample.LibraryService.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

/**
 * Filter to validate JWT token sent in header, before each service in Library
 * Controller
 * 
 * @author Sahana
 *
 */
public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		String TOKEN_PREFIX = "Bearer ";
		String HEADER_STRING = "Authorization";
		String JWT_SECRET = "secret";

		final HttpServletRequest request = (HttpServletRequest) req;

		final String authHeader = request.getHeader(HEADER_STRING);

		if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
			throw new ServletException("Token not found");
		}

		final String compactJws = authHeader.substring(TOKEN_PREFIX.length());

		try {
			final Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
					.parseClaimsJws(compactJws).getBody();
			// We can trust this JWT
			request.setAttribute("username", claims.get("username"));
		} catch (Exception e) {
			// don't trust the JWT!
			throw new JwtException("Invalid JWT token", e);
		}

		chain.doFilter(req, res);
	}

}
