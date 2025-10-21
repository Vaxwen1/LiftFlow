package com.liftflow.service;

import com.liftflow.model.DonationJar;
import com.liftflow.repository.DonationJarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class DonationJarService {

    @Autowired
    private DonationJarRepository repository;

    public List<DonationJar> getAll() {
        return repository.findAll();
    }

    public Optional<DonationJar> getById(Integer id) {
        return repository.findById(id);
    }

    public DonationJar create(@Valid DonationJar jar) {
        jar.setCreatedAt(LocalDateTime.now());
        jar.setUpdatedAt(LocalDateTime.now());
        jar.setCurrentAmount(jar.getCurrentAmount() != null ? jar.getCurrentAmount() : BigDecimal.ZERO);
        return repository.save(jar);
    }

    public DonationJar update(Integer id, @Valid DonationJar updatedJar) {
        return repository.findById(id).map(jar -> {
            jar.setJarName(updatedJar.getJarName());
            jar.setTargetAmount(updatedJar.getTargetAmount());
            jar.setDescription(updatedJar.getDescription());
            jar.setStartDate(updatedJar.getStartDate());
            jar.setEndDate(updatedJar.getEndDate());
            jar.setUpdatedAt(LocalDateTime.now());
            return repository.save(jar);
        }).orElseThrow(() -> new RuntimeException("DonationJar not found with id " + id));
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("DonationJar not found with id " + id);
        }
        repository.deleteById(id);
    }

    public DonationJar addDonation(Integer id, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Donation amount must be positive");
        }
        return repository.findById(id).map(jar -> {
            jar.setCurrentAmount(jar.getCurrentAmount().add(amount));
            jar.setUpdatedAt(LocalDateTime.now());
            return repository.save(jar);
        }).orElseThrow(() -> new RuntimeException("DonationJar not found with id " + id));
    }
}