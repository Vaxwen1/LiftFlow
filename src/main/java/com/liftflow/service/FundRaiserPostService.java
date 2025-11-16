package com.liftflow.service;
import com.liftflow.model.FundRaiserPost;
import com.liftflow.model.User;
import com.liftflow.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundRaiserPostService{

    private final FundRaiserPostRepository repo;

    public FundRaiserPostService(FundRaiserPostRepository repo) { this.repo = repo; }

    public List<FundRaiserPost> findAll() { return repo.findAll(); }

    public FundRaiserPost create(FundRaiserPost fp) {
        if (fp.getCreatedAt() == null) fp.setCreatedAt(LocalDateTime.now());
        fp.setUpdatedAt(LocalDateTime.now());
        return repo.save(fp);
    }

}
