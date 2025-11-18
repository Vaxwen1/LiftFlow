package com.liftflow.service;
import com.liftflow.model.DonationJar;
import com.liftflow.model.FundRaiserPost;
import com.liftflow.model.User;
import com.liftflow.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundRaiserPostService {

    private final FundRaiserPostRepository repo;
    private final DonationJarRepository jarRepo;

    public FundRaiserPostService(FundRaiserPostRepository repo, DonationJarRepository jarRepo) {
        this.repo = repo;
        this.jarRepo = jarRepo;
    }

    public List<FundRaiserPost> findAll() {
        return repo.findAll();
    }

    public List<FundRaiserPost> findByJarId(Integer jarId) {
        return repo.findByDonationJar_JarId(jarId);
    }

    public FundRaiserPost createPost(Integer jarId, FundRaiserPost post) {

        DonationJar jar = jarRepo.findById(jarId)
                .orElseThrow(() -> new RuntimeException("Jar not found"));

        post.setDonationJar(jar);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        return repo.save(post);
    }
}
