package apap.ta.rumahsehat.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.ta.rumahsehat.model.JwtResponse;
import apap.ta.rumahsehat.model.UserModel;
import apap.ta.rumahsehat.config.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1/")
public class JwtAuthenticationController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenUtil jwtTokenUtil;
  
  @PostMapping("/login")
  public ResponseEntity<?> userLogin(@RequestBody UserModel user) {

    Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtTokenUtil.generateJwtToken(authentication);
   
    JwtResponse authResponse = new JwtResponse();
    authResponse.setToken(token);
    
    return ResponseEntity.ok(authResponse);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> userLogout() {
    
    String token = jwtTokenUtil.refreshJwtToken();
   
    JwtResponse authResponse = new JwtResponse();
    authResponse.setToken(token);
    
    return ResponseEntity.ok(authResponse);
  }

}