package com.liftflow.repository;

import com.liftflow.model.Donation;
import com.liftflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    List<Donation> findByUser(User user);
}
