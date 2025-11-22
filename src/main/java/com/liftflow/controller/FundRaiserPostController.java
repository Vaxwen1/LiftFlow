package com.liftflow.controller;

import com.liftflow.model.FundRaiserPost;
import com.liftflow.service.FundRaiserPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class FundRaiserPostController {

    private final FundRaiserPostService service;

    public FundRaiserPostController(FundRaiserPostService service) {
        this.service = service;
    }

    // All posts
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", service.findAll());
        return "Posts/posts";
    }

    // Form
    @GetMapping("/new/{jarId}")
    public String newPost(@PathVariable Integer jarId, Model model) {
        model.addAttribute("post", new FundRaiserPost());
        model.addAttribute("jarId", jarId);
        return "Posts/post_form";
    }

    // Create
    @PostMapping("/create/{jarId}")
    public String create(@PathVariable Integer jarId,
                         @ModelAttribute FundRaiserPost post) {

        service.createPost(jarId, post);
        return "redirect:/posts";
    }
}
