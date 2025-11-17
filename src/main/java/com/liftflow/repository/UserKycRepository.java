package com.liftflow.repository;

import com.liftflow.model.UserKyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKycRepository extends JpaRepository<UserKyc, Integer> {
    // no custom methods needed for now
}
