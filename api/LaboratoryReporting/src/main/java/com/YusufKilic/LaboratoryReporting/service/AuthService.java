package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.LoginRequest;
import com.YusufKilic.LaboratoryReporting.dto.TokenResponseDto;
import com.YusufKilic.LaboratoryReporting.dto.UserDtoConverter;
import com.YusufKilic.LaboratoryReporting.exception.AuthenticationException;
import com.YusufKilic.LaboratoryReporting.model.User;
import com.YusufKilic.LaboratoryReporting.utils.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;
    private final UserDtoConverter converter;

    public AuthService(UserService userService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager, UserDtoConverter converter) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
        this.converter = converter;
    }

    public TokenResponseDto login(LoginRequest request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.username(),
                                    request.password()));
            User user = userService.findUserByUsername(request.username());
            return new TokenResponseDto(tokenGenerator.generateToken(authentication), converter.converter(user));
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
