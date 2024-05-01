package ru.dmitrychin.jwtTestProject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dmitrychin.jwtTestProject.model.RoleType;
import ru.dmitrychin.jwtTestProject.model.User;
import ru.dmitrychin.jwtTestProject.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void testSaveNewUser() throws ParseException {
        User user1 = new User(1L, "test-test", "test-test", RoleType.USER);
        userService.saveNewUser(user1);

        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void findByUsername() throws ParseException {
        User user1 = new User(1L, "test-test", "test-test", RoleType.USER);
        Mockito.when(userRepository.findByUsername("test-test")).thenReturn(Optional.of(user1));

        User userToFind = userService.getByUsername("test-test");

        verify(userRepository, times(1)).findByUsername("test-test");
    }
}
