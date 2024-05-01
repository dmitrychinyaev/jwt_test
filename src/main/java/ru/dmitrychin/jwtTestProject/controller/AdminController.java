package ru.dmitrychin.jwtTestProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "Реализация аутентификации и авторизации с использованием Spring Security и JWT")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello, admin!");
    }
}

