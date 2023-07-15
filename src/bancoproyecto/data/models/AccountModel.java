package bancoproyecto.data.models;

import java.util.UUID;

public class AccountModel {
    private UUID uuid;
    private String description;
    private Double balance;

    public AccountModel(String description, Double balance) {
        this.uuid = UUID.randomUUID();
        this.description = description;
        this.balance = balance;
    }

    public AccountModel(String uuid, String description, Double balance) {
        this.uuid = UUID.fromString(uuid);
        this.description = description;
        this.balance = balance;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("UUID: %s, Name: %s, Balance: %s", uuid, description, balance);
    }
}
