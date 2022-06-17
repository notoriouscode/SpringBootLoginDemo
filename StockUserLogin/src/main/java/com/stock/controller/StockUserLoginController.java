package com.stock.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.x.Ok;
import com.stock.dto.LoginRequest;
import com.stock.dto.StockUserDtoResponse;
import com.stock.entity.JwtBlackListedTokenDocument;
import com.stock.service.StockUserService;
import com.stock.service.UserBlackListedTokenService;
import com.stock.utils.JwtUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mymarketplace") 
public class StockUserLoginController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	StockUserService stockUserService;
	@Autowired
	UserBlackListedTokenService userBlackListedTokenService;
	
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String jwtToken){
    	userBlackListedTokenService.saveBlackListedToke(jwtToken);
    	return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
	
	// for login(pass username and password and get the JWT token)
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockUserDtoResponse> authencate(@RequestBody LoginRequest authRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			return new ResponseEntity<StockUserDtoResponse>(HttpStatus.BAD_REQUEST);
		}
		StockUserDtoResponse userDto = stockUserService.findByUsername(authRequest.getUsername());
		userDto.setJwtToken(jwtUtils.generateToken(authRequest.getUsername()));
		return new ResponseEntity<StockUserDtoResponse>(userDto, HttpStatus.OK);
		
	}
	
	//to verify the token (In postman go to authorization and select Bearer token)
	@GetMapping(value="/login/token/validate")
	public ResponseEntity<Boolean> isTokenValid(@RequestHeader("Authorization") String jwtToken) {
		jwtToken = jwtToken.substring(7, jwtToken.length());
		String username = jwtUtils.extractUsername(jwtToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		boolean isTokenValid = jwtUtils.validateToken(jwtToken, userDetails);
    	
//      do this by user name		
//		List<JwtBlackListedTokenDocument> mBlockListedToken = userBlackListedTokenService.getAllBlackListedTokens();
//		for(int i=0;i<mBlockListedToken.size();i++) {
//			JwtBlackListedTokenDocument jwtBlackListedTokenDocument = mBlockListedToken.get(i);
//			if(jwtToken.equals(jwtBlackListedTokenDocument.getJwtToken())) {
//				isTokenValid=false;
//				userBlackListedTokenService.deleteToken(jwtBlackListedTokenDocument);
//				break;
//			}
//		}
    	if(isTokenValid)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
}
