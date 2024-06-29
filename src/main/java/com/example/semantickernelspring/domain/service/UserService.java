package com.example.semantickernelspring.domain.service;

import com.example.semantickernelspring.domain.model.User;
import com.example.semantickernelspring.domain.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        log.info("Creating user: {}", user);
        User persistedUser = userRepository.save(user);
        return persistedUser;
    }


    public Optional<User> findByUsername(String username) {
        log.info("Finding user by username: {}", username);
        return userRepository.findFirstByUsername(username);
    }

    public User updateUser(User user){
        log.info("Updating user: {}", user);
        return userRepository.save(user);
    }

    public void deleteAll(){
        log.info("Deleting all users");
        userRepository.deleteAll();
    }

}
