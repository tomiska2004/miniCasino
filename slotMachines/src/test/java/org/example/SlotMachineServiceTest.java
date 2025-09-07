package org.example;


import org.example.exception.InsufficientBalanceException;
import org.example.model.Player;
import org.example.repository.PlayerRepository;
import org.example.service.SlotMachineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
class SlotMachineServiceTest {

    private PlayerRepository playerRepository;
    private SlotMachineService slotMachineService;

    @BeforeEach
    void setUp() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        slotMachineService = new SlotMachineService(playerRepository);
    }

    @Test
    void testSpinWithEnoughBalance() {
        Player player = new Player("test", BigDecimal.valueOf(100));
        player.setBalance(BigDecimal.valueOf(100));
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        slotMachineService.spin(1L, BigDecimal.valueOf(10));

        assertTrue(player.getBalance().compareTo(BigDecimal.ZERO) >= 0);
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void testSpinWithInsufficientBalance() {
        Player player = new Player("test", BigDecimal.valueOf(5));
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        assertThrows(InsufficientBalanceException.class,
                () -> slotMachineService.spin(1L, BigDecimal.valueOf(10)));
    }
}
