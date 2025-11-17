package com.liftflow.repository;

import com.liftflow.model.DonationJar;
import com.liftflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationJarRepository extends JpaRepository<DonationJar, Integer> {
    List<DonationJar> findByCreatedBy(User user);
}
