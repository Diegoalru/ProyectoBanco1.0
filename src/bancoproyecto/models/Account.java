package bancoproyecto.models;

import java.util.UUID;

public class Account {
    private UUID accountNumber;
    private String name;
    private Double balance;

    public Account(UUID accountNumber, String name, Double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
