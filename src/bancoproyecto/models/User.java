package bancoproyecto.models;

import java.util.List;

public class User {
    private String user;
    private List<Account> accounts;

    public User(String user) {
        this.user = user;
    }

    public User(String user, List<Account> accounts) {
        this.user = user;
        this.accounts = accounts;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
