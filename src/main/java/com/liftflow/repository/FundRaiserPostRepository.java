package com.liftflow.repository;

import com.liftflow.model.FundRaiserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundRaiserPostRepository extends JpaRepository<FundRaiserPost, Integer> {
    List<FundRaiserPost> findByDonationJar_JarId(Integer jarId);
}
