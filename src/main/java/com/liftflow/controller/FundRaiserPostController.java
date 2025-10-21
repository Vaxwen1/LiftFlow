package com.liftflow.controller;

import com.liftflow.model.FundRaiserPost;
import com.liftflow.model.User;
import com.liftflow.service.FundRaiserPostService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class FundRaiserPostController {
    private final FundRaiserPostService service;

    public FundRaiserPostController(FundRaiserPostService service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public String fundRaiserPosts(Model model) {
        model.addAttribute("posts", service.findAll());
        return "posts";
    }

    @GetMapping("/posts/new")
    public String newFundRaiserPostForm(Model model) {
        FundRaiserPost fp = new FundRaiserPost();
        fp.setRole('F');
        fp.setStatus('A');
        model.addAttribute("post", fp);
        return "post_form";
    }

    @PostMapping("/posts")
    public String createFundRaiserPost(@ModelAttribute("user") FundRaiserPost fp) {
        service.create(fp); // createdAt/updatedAt выставятся в сервисе
        return "redirect:/";
    }

}
