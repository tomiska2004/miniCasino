package org.example.service;

import org.example.exception.InsufficientBalanceException;
import org.example.model.Player;
import org.example.model.SpinResult;
import org.example.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SlotMachineService {

    private final PlayerRepository playerRepository;
    private final Random random = new Random();

    private static final List<String> SYMBOLS = Arrays.asList("🍒", "🍋", "⭐", "💎");

    public SlotMachineService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public SpinResult spin(Long playerId, BigDecimal bet) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (player.getBalance().compareTo(bet) < 0) {
            throw new InsufficientBalanceException("Not enough balance");
        }

        player.setBalance(player.getBalance().subtract(bet));

        List<String> result = Arrays.asList(
                SYMBOLS.get(random.nextInt(SYMBOLS.size())),
                SYMBOLS.get(random.nextInt(SYMBOLS.size())),
                SYMBOLS.get(random.nextInt(SYMBOLS.size()))
        );

        BigDecimal winAmount = calculateWin(result, bet);
        player.setBalance(player.getBalance().add(winAmount));

        playerRepository.save(player);

        return new SpinResult(result, winAmount, player.getBalance());
    }

    private BigDecimal calculateWin(List<String> symbols, BigDecimal bet) {
        if (symbols.get(0).equals(symbols.get(1)) && symbols.get(1).equals(symbols.get(2))) {
            return bet.multiply(BigDecimal.valueOf(5));
        }else
            if (symbols.get(0).equals(symbols.get(1)) || symbols.get(1).equals(symbols.get(2))) {
            return bet.multiply(BigDecimal.valueOf(0.5));
        }
        return BigDecimal.ZERO;
    }
}
