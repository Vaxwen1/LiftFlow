package com.liftflow.repository;

import com.liftflow.model.DonationJar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationJarRepository extends JpaRepository<DonationJar, Integer> {
}