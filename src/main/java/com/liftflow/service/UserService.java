package com.liftflow.service;

import com.liftflow.model.User;
import com.liftflow.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    /** Get all users */
    public List<User> findAll() {
        return repo.findAll();
    }

    /** Find a user by ID */
    public User findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /** Create new user */
    public User create(User u) {
        if (u.getCreatedAt() == null) {
            u.setCreatedAt(LocalDateTime.now());
        }
        u.setUpdatedAt(LocalDateTime.now());
        return repo.save(u);
    }

    /** Update existing user (explicit, safe copying) */
    public User update(Integer id, User incoming) {
        User existing = findById(id);

        if (incoming.getUsername() != null && !incoming.getUsername().trim().isEmpty()) {
            existing.setUsername(incoming.getUsername().trim());
        }
        if (incoming.getEmail() != null && !incoming.getEmail().trim().isEmpty()) {
            existing.setEmail(incoming.getEmail().trim());
        }
        if (incoming.getPassword() != null && !incoming.getPassword().trim().isEmpty()) {
            // TODO: hash password in production
            existing.setPassword(incoming.getPassword());
        }
        if (incoming.getFirstName() != null && !incoming.getFirstName().trim().isEmpty()) {
            existing.setFirstName(incoming.getFirstName());
        }
        if (incoming.getLastName() != null && !incoming.getLastName().trim().isEmpty()) {
            existing.setLastName(incoming.getLastName());
        }
        if (incoming.getPhoneNumber() != null && !incoming.getPhoneNumber().trim().isEmpty()) {
            existing.setPhoneNumber(incoming.getPhoneNumber());
        }

        // Only set if provided (not default '\0')
        if (incoming.getRole() != '\0') {
            existing.setRole(incoming.getRole());
        }
        if (incoming.getStatus() != '\0') {
            existing.setStatus(incoming.getStatus());
        }

        existing.setUpdatedAt(LocalDateTime.now());
        return repo.save(existing);
    }

    /** Delete a user */
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        repo.deleteById(id);
    }
}
