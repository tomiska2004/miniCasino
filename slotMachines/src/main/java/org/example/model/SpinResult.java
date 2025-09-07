package org.example.model;


import java.math.BigDecimal;
import java.util.List;

public class SpinResult {
    private List<String> symbols;
    private BigDecimal winAmount;
    private BigDecimal newBalance;

    public SpinResult(List<String> symbols, BigDecimal winAmount, BigDecimal newBalance) {
        this.symbols = symbols;
        this.winAmount = winAmount;
        this.newBalance = newBalance;
    }

    public List<String> getSymbols() { return symbols; }
    public BigDecimal getWinAmount() { return winAmount; }
    public BigDecimal getNewBalance() { return newBalance; }
}
