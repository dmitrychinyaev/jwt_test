package ru.dmitrychin.jwtTestProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "Реализация аутентификации и авторизации с использованием Spring Security и JWT")
public class UserController {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello, user!");
    }

}
