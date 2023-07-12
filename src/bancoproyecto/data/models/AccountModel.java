package bancoproyecto.data.models;

import java.rmi.server.UID;

public class AccountModel {
    private UID GUID;
    private String name;
    private Double balance;

    public AccountModel(String name, Double balance) {
        this.GUID = new UID();
        this.name = name;
        this.balance = balance;
    }

    public AccountModel(UID GUID, String name, Double balance) {
        this.GUID = GUID;
        this.name = name;
        this.balance = balance;
    }

    public UID getGUID() {
        return GUID;
    }

    public void setGUID(UID GUID) {
        this.GUID = GUID;
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
        return String.format("UID: %s, Name: %s, Balance: %s", this.GUID, this.name, this.balance);
    }
}
