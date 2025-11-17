package com.liftflow.service;

import com.liftflow.model.DonationJar;
import com.liftflow.repository.DonationJarRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

        jar.setJarName(jar.getJarName());
        jar.setTargetAmount(jar.getTargetAmount());
//        jar.setCurrentAmount(BigDecimal.valueOf(0));
        jar.setCurrentAmount(jar.getCurrentAmount());
        jar.setTargetAmount(jar.getTargetAmount());
        jar.setEndDate(jar.getEndDate());

        jar.setCreatedBy(jar.getCreatedBy());
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

//    public void delete(Integer id) {
//        if (!repository.existsById(id)) {
//            throw new RuntimeException("DonationJar not found with id " + id);
//        }
//        repository.deleteById(id);
//    }

    @Transactional
    public void delete(Integer id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Cannot delete jar because it contains donations.");
        }
    }

}