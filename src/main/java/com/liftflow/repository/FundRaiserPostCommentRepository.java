package com.liftflow.repository;

import com.liftflow.model.FundRaiserPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundRaiserPostCommentRepository extends JpaRepository<FundRaiserPostComment, Integer> {
    List<FundRaiserPostComment> findByPost_PostId(Integer postId);
}
