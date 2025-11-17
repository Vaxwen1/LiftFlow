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
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with ID " + id));
    }

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User update(Integer id, User updatedUser) {
        User existing = findById(id);

        if (updatedUser.getUsername() != null) existing.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null) existing.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) existing.setPassword(updatedUser.getPassword());
        if (updatedUser.getFirstName() != null) existing.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) existing.setLastName(updatedUser.getLastName());
        if (updatedUser.getPhoneNumber() != null) existing.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getRole() != '\0') existing.setRole(updatedUser.getRole());
        if (updatedUser.getStatus() != '\0') existing.setStatus(updatedUser.getStatus());

        existing.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existing);
    }

    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID " + id);
        }
        userRepository.deleteById(id);
    }
}
