package ru.dmitrychin.jwtTestProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.dmitrychin.jwtTestProject.service.AuthenticationService;
import ru.dmitrychin.jwtTestProject.model.dto.JwtAuthenticationResponse;
import ru.dmitrychin.jwtTestProject.model.dto.SignInRequest;
import ru.dmitrychin.jwtTestProject.model.dto.SignUpRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Реализация аутентификации и авторизации с использованием Spring Security и JWT")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация")
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        var response = authenticationService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Авторизация")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Valid SignInRequest request) {
        var response = authenticationService.signIn(request);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
