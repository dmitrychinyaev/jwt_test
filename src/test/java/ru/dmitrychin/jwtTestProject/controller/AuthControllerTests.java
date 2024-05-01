package ru.dmitrychin.jwtTestProject.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.dmitrychin.jwtTestProject.model.dto.JwtAuthenticationResponse;
import ru.dmitrychin.jwtTestProject.model.dto.SignInRequest;
import ru.dmitrychin.jwtTestProject.model.dto.SignUpRequest;
import ru.dmitrychin.jwtTestProject.service.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Mock
    AuthenticationService authenticationService;
    @InjectMocks
    AuthController authController;

    @Test
    void signUpTest() throws Exception {
        SignUpRequest requestTest = new SignUpRequest("test_test", "test");

        Mockito.when(authenticationService.signUp(requestTest)).thenReturn(new JwtAuthenticationResponse("testToken"));

        var jwtResponse = this.authController.signUp(requestTest);

        assertNotNull(jwtResponse);
        assertEquals(HttpStatus.OK, jwtResponse.getStatusCode());
    }

    @Test
    void signInTest() throws Exception {
        SignInRequest requestTest = new SignInRequest("test_test", "test");

        Mockito.when(authenticationService.signIn(requestTest)).thenReturn(new JwtAuthenticationResponse("testToken"));

        var jwtResponse = this.authController.signIn(requestTest);

        assertNotNull(jwtResponse);
        assertEquals(HttpStatus.OK, jwtResponse.getStatusCode());
    }

}

