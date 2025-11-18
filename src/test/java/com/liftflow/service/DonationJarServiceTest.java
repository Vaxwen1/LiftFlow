package com.liftflow.service;

import com.liftflow.model.DonationJar;
import com.liftflow.repository.DonationJarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonationJarServiceTest {

    private DonationJarRepository repository;
    private DonationJarService service;

    @BeforeEach
    void setUp() {
        repository = mock(DonationJarRepository.class);
        service = new DonationJarService(repository);
    }

    @Test
    void getAll_returnsFromRepository() {
        DonationJar j1 = new DonationJar();
        DonationJar j2 = new DonationJar();
        when(repository.findAll()).thenReturn(List.of(j1, j2));

        List<DonationJar> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void getById_delegatesToRepository() {
        DonationJar jar = new DonationJar();
        jar.setJarId(1);
        when(repository.findById(1)).thenReturn(Optional.of(jar));

        Optional<DonationJar> result = service.getById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getJarId());
        verify(repository).findById(1);
    }

    @Test
    void create_setsDefaultsAndSaves() {
        DonationJar jar = new DonationJar();
        jar.setJarName("Help");
        jar.setTargetAmount(new BigDecimal("100"));

        when(repository.save(any(DonationJar.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        DonationJar saved = service.create(jar);

        assertEquals("Help", saved.getJarName());
        assertEquals(new BigDecimal("100"), saved.getTargetAmount());
        assertNotNull(saved.getCreatedAt());
        assertNotNull(saved.getUpdatedAt());
        assertEquals(BigDecimal.ZERO, saved.getCurrentAmount());
        verify(repository).save(any(DonationJar.class));
    }

    @Test
    void update_existing_updatesFields() {
        DonationJar existing = new DonationJar();
        existing.setJarId(1);
        existing.setJarName("Old");
        existing.setTargetAmount(new BigDecimal("50"));
        existing.setDescription("Old desc");
        existing.setStartDate(LocalDate.from(LocalDateTime.now().minusDays(5)));
        existing.setEndDate(LocalDate.from(LocalDateTime.now().plusDays(5)));

        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(any(DonationJar.class))).thenAnswer(inv -> inv.getArgument(0));

        DonationJar updated = new DonationJar();
        updated.setJarName("New");
        updated.setTargetAmount(new BigDecimal("200"));
        updated.setDescription("New desc");
        updated.setStartDate(LocalDate.from(LocalDateTime.now()));
        updated.setEndDate(LocalDate.from(LocalDateTime.now().plusDays(10)));

        DonationJar result = service.update(1, updated);

        assertEquals("New", result.getJarName());
        assertEquals(new BigDecimal("200"), result.getTargetAmount());
        assertEquals("New desc", result.getDescription());
        assertEquals(updated.getStartDate(), result.getStartDate());
        assertEquals(updated.getEndDate(), result.getEndDate());
        verify(repository).save(existing);
    }

    @Test
    void update_missing_throwsRuntime() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.update(1, new DonationJar()));

        assertTrue(ex.getMessage().contains("DonationJar not found with id 1"));
    }

    @Test
    void delete_ok_callsRepository() {
        service.delete(10);
        verify(repository).deleteById(10);
    }

    @Test
    void delete_violation_throwsIllegalState() {
        doThrow(new DataIntegrityViolationException("FK"))
                .when(repository).deleteById(10);

        IllegalStateException ex =
                assertThrows(IllegalStateException.class, () -> service.delete(10));

        assertTrue(ex.getMessage().contains("contains donations"));
    }
}
