package bancoproyecto.data.models;

import java.util.UUID;

public class AccountModel {
    private UUID uuid;
    private Integer userId;
    private String description;
    private Double balance;
    private Integer status;

    public AccountModel(String description, Double balance) {
        this.description = description;
        this.balance = balance;
    }

    /**
     * Constructor de la clase {@link AccountModel} usando al obtener las cuentas de un usuario.
     * @param uuid {@link UUID} que representa el UUID de la cuenta.
     * @param userId {@link Integer} que representa el ID del usuario.
     * @param description {@link String} que representa la descripción de la cuenta.
     * @param balance {@link Double} que representa el balance de la cuenta.
     * @param status {@link Integer} que representa el status de la cuenta.
     */
    public AccountModel(String uuid, Integer userId, String description, Double balance, Integer status) {
        this.uuid = UUID.fromString(uuid);
        this.userId = userId;
        this.description = description;
        this.balance = balance;
        this.status = status;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("UUID: %s, Name: %s, Balance: %s, Status: %d", uuid, description, balance, status);
    }
}
