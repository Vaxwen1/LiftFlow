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

    // GET /posts/
    @GetMapping("/")
    public String fundRaiserPosts(Model model) {
        model.addAttribute("posts", service.findAll());
        return "posts";
    }

    // GET /posts/new
    @GetMapping("/new")
    public String newFundRaiserPostForm(Model model) {
        FundRaiserPost fp = new FundRaiserPost();
        fp.setPostType("text");
        fp.setLikesCount(0);
        fp.setCommentsCount(0);

        model.addAttribute("post", fp);
        return "post_form";
    }

    // POST /posts
    @PostMapping
    public String createFundRaiserPost(@ModelAttribute("post") FundRaiserPost fp) {
        service.create(fp);
        return "redirect:/posts/";
    }
}
