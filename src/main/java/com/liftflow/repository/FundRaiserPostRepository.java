package com.liftflow.repository;

import com.liftflow.model.FundRaiserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRaiserPostRepository extends JpaRepository<FundRaiserPost, Integer> {
}
