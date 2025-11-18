package com.liftflow.service;

import com.liftflow.model.DonationJar;
import com.liftflow.model.FundRaiserPost;
import com.liftflow.repository.DonationJarRepository;
import com.liftflow.repository.FundRaiserPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundRaiserPostServiceTest {

    private FundRaiserPostRepository repo;
    private DonationJarRepository jarRepo;
    private FundRaiserPostService service;

    @BeforeEach
    void setUp() {
        repo = mock(FundRaiserPostRepository.class);
        jarRepo = mock(DonationJarRepository.class);
        service = new FundRaiserPostService(repo, jarRepo);
    }

    @Test
    void findAll_returnsFromRepository() {
        when(repo.findAll()).thenReturn(List.of(new FundRaiserPost()));

        List<FundRaiserPost> result = service.findAll();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }

    @Test
    void findByJarId_delegatesToRepository() {
        when(repo.findByDonationJar_JarId(Integer.valueOf(5))).thenReturn(List.of(new FundRaiserPost()));

        List<FundRaiserPost> result = service.findByJarId(Integer.valueOf(5));

        assertEquals(1, result.size());
        verify(repo).findByDonationJar_JarId(Integer.valueOf(5));
    }

    @Test
    void createPost_jarExists_setsJarAndTimestampsAndSaves() {
        DonationJar jar = new DonationJar();
        jar.setJarId(Integer.valueOf(7));
        when(jarRepo.findById(Integer.valueOf(7))).thenReturn(Optional.of(jar));
        when(repo.save(any(FundRaiserPost.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        FundRaiserPost post = new FundRaiserPost();
        post.setTitle("Test");

        FundRaiserPost saved = service.createPost(Integer.valueOf(7), post);

        assertEquals("Test", saved.getTitle());
        assertNotNull(saved.getDonationJar());
        assertEquals(7, saved.getDonationJar().getJarId());
        assertNotNull(saved.getCreatedAt());
        assertNotNull(saved.getUpdatedAt());
        verify(repo).save(post);
    }

    @Test
    void createPost_jarMissing_throwsRuntime() {
        when(jarRepo.findById(Integer.valueOf(7))).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.createPost(Integer.valueOf(7), new FundRaiserPost()));

        assertTrue(ex.getMessage().contains("Jar not found"));
    }
}
