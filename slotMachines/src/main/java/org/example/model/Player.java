package org.example.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private BigDecimal balance;

    public Player() {}

    public Player(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
