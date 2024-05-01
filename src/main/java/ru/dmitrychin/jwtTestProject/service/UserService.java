package ru.dmitrychin.jwtTestProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitrychin.jwtTestProject.model.User;
import ru.dmitrychin.jwtTestProject.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public void saveNewUser(User user) {
        repository.save(user);
    }

    @Transactional
    public void createNewUser(User user) {
        saveNewUser(user);
    }

    @Transactional
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }


}