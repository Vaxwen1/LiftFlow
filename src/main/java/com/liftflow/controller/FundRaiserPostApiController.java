package com.liftflow.controller;

import com.liftflow.repository.FundRaiserPostLikeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class FundRaiserPostApiController {
    private final FundRaiserPostLikeRepository likeRepository;
    public FundRaiserPostApiController(FundRaiserPostLikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @GetMapping("/{id}/likes/count")
    public Map<String, Object> likesCount(@PathVariable Integer id) {
        long count = likeRepository.countByPost_PostId(id);
        return Map.of("count", Long.valueOf(count)); // FIXED
    }

}
