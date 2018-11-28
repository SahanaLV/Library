package com.sample.LibraryService.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.LibraryService.model.Login;
import com.sample.LibraryService.service.LibraryService;
import com.sample.LibraryService.util.PasswordUtils;

/**
 * Login Controller contains end point for login
 * 
 * @author User
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	public static final long JWT_EXPIRATION_TIME = 30;
	public static final String JWT_SECRET = "secret";

	@Autowired
	private LibraryService service;

	/**
	 * This method checks whether user is present in admin table If present it
	 * returns hashed password stored in database Password passed from login
	 * page is hashed and compared with password returned from db If password
	 * matches then generate JWT token Token is sent in the response
	 * 
	 * @param login
	 * @return jwt Token
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String login(@RequestBody Login login) {
		String securePassword = service.validateUser(login.getUserName());
		if (securePassword != null) {
			String salt = "3YgZ12to9T8clX41EK99uENfw4rre6";
			if (PasswordUtils.verifyUserPassword(login.getPassword(),
					securePassword, salt)) {
				return generateToken(login.getUserName());
			} else
				return "Password invalid";
		} else
			return "User not present";
	}

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", userName);
		String jwt = Jwts
				.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(
						new Date(System.currentTimeMillis()
								+ JWT_EXPIRATION_TIME * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
		return "Token: " + jwt;
	}

}
