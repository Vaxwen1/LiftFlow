package com.liftflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FundRaiserPosts", schema = "dbo")
public class FundRaiserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    // NEW — correct field for jar_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jar_id", nullable = false)
    private DonationJar donationJar;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "post_type", nullable = false)
    private String postType = "text";

    @Column(name = "likes_count", nullable = false)
    private Integer likesCount = 0;

    @Column(name = "comments_count", nullable = false)
    private Integer commentsCount = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FundRaiserPostLike> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FundRaiserPostComment> comments;

    public FundRaiserPost() {}

    // --------------------------------------------------------------------
    // GETTERS AND SETTERS
    // --------------------------------------------------------------------

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    // ✔ FIXED — correct getter/setter for jar_id (DonationJar)
    public DonationJar getDonationJar() {
        return donationJar;
    }

    public void setDonationJar(DonationJar donationJar) {
        this.donationJar = donationJar;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<FundRaiserPostLike> getLikes() {
        return likes;
    }

    public void setLikes(List<FundRaiserPostLike> likes) {
        this.likes = likes;
    }

    public List<FundRaiserPostComment> getComments() {
        return comments;
    }

    public void setComments(List<FundRaiserPostComment> comments) {
        this.comments = comments;
    }

    public String getTitle()
    {
        return postTitle;
    }

    public void setTitle(String test)
    {
        this.postTitle = test;
    }
}
