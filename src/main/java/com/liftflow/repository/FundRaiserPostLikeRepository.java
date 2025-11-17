package com.liftflow.repository;

import com.liftflow.model.FundRaiserPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FundRaiserPostLikeRepository extends JpaRepository<FundRaiserPostLike, Integer> {
    Optional<FundRaiserPostLike> findByPost_PostIdAndUsername(Integer postId, String username);
    long countByPost_PostId(Integer postId);
}
