package com.noirix.controller;

import com.noirix.controller.requests.AuthRequest;
import com.noirix.controller.requests.AuthResponse;
import com.noirix.security.jwt.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenHelper tokenUtils;

    private final UserDetailsService userProvider;

    //    @ApiOperation(value = "Login user in system", notes = "Return Auth-Token with user login")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful authorization"),
//            @ApiResponse(code = 400, message = "Request error"),
//            @ApiResponse(code = 500, message = "Server error")
//    })
    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .username(request.getLogin())
                        .token(tokenUtils.generateToken(userProvider.loadUserByUsername(request.getLogin())))
                        .build()
        );
    }

    //localhost:8080/authentication POST
/*
    {

    "login": "login",
    "password": "password"

   }   -> new AuthRequest(login, password) */
}
