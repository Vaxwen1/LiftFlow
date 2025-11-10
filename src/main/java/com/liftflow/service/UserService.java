package com.liftflow.service;

import com.liftflow.model.User;
import com.liftflow.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --- Get all users ---
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // --- Get single user by ID ---
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with ID " + id));
    }

    // --- Create new user ---
    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // --- Update existing user ---
    public User update(Integer id, User updatedUser) {
        User existingUser = findById(id);

        if (updatedUser.getUsername() != null)
            existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null)
            existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null)
            existingUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getFirstName() != null)
            existingUser.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null)
            existingUser.setLastName(updatedUser.getLastName());
        if (updatedUser.getPhoneNumber() != null)
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getRole() != '\0')
            existingUser.setRole(updatedUser.getRole());
        if (updatedUser.getStatus() != '\0')
            existingUser.setStatus(updatedUser.getStatus());

        existingUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existingUser);
    }

    // --- Delete user by ID ---
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID " + id);
        }
        userRepository.deleteById(id);
    }
}
