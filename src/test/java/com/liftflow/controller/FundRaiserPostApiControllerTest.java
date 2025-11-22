package com.liftflow.controller;

import com.liftflow.repository.FundRaiserPostLikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundRaiserPostApiControllerTest {

    private FundRaiserPostLikeRepository likeRepository;
    private FundRaiserPostApiController controller;

    @BeforeEach
    void setUp() {
        likeRepository = mock(FundRaiserPostLikeRepository.class);
        controller = new FundRaiserPostApiController(likeRepository);
    }

    @Test
    void likesCount_returnsCountInMap() {
        when(likeRepository.countByPost_PostId(5)).thenReturn(7L);

        Map<String, Object> result = controller.likesCount(5);

        assertTrue(result.containsKey("count"));
        assertEquals(7L, result.get("count"));
        verify(likeRepository).countByPost_PostId(5);
    }
}
