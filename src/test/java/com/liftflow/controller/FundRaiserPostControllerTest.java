package com.liftflow.controller;

import com.liftflow.model.FundRaiserPost;
import com.liftflow.service.FundRaiserPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundRaiserPostControllerTest {

    private FundRaiserPostService service;
    private FundRaiserPostController controller;

    @BeforeEach
    void setUp() {
        service = mock(FundRaiserPostService.class);
        controller = new FundRaiserPostController(service);
    }

    @Test
    void list_addsPostsToModel_andReturnsView() {
        Model model = new ExtendedModelMap();
        List<FundRaiserPost> posts = List.of(new FundRaiserPost(), new FundRaiserPost());

        when(service.findAll()).thenReturn(posts);

        String view = controller.list(model);

        assertEquals("Posts/posts", view);
        assertTrue(model.containsAttribute("posts"));
        assertEquals(posts, model.getAttribute("posts"));
        verify(service).findAll();
    }

    @Test
    void newPost_addsEmptyPostAndJarId_andReturnsFormView() {
        Model model = new ExtendedModelMap();

        String view = controller.newPost(Integer.valueOf(5), model);

        assertEquals("Posts/post_form", view);
        assertTrue(model.containsAttribute("post"));
        assertTrue(model.getAttribute("post") instanceof FundRaiserPost);
        assertTrue(model.containsAttribute("jarId"));
        assertEquals((short) 5, (Short) model.getAttribute("jarId"));
    }

    @Test
    void create_callsServiceAndRedirects() {
        FundRaiserPost post = new FundRaiserPost();

        String view = controller.create(Integer.valueOf(10), post);

        assertEquals("redirect:/posts", view);
        verify(service).createPost(Integer.valueOf(10), post);
    }
}
