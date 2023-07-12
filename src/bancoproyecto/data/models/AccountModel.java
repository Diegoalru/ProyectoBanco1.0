package bancoproyecto.data.models;

import java.util.UUID;

public class AccountModel {
    private UUID uuid;
    private String name;
    private Double balance;

    public AccountModel(String name, Double balance) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.balance = balance;
    }

    public AccountModel(UUID uuid, String name, Double balance) {
        this.uuid = uuid;
        this.name = name;
        this.balance = balance;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
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

    @Override
    public String toString() {
        return String.format("UUID: %s, Name: %s, Balance: %s", uuid, name, balance);
    }
}
