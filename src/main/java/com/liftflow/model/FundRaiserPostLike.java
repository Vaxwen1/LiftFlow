package com.liftflow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "FundRaiserPostLikes", schema = "dbo")
public class FundRaiserPostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private FundRaiserPost post;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and setters
    public Integer getLikeId() { return likeId; }
    public void setLikeId(Integer likeId) { this.likeId = likeId; }

    public FundRaiserPost getPost() { return post; }
    public void setPost(FundRaiserPost post) { this.post = post; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
