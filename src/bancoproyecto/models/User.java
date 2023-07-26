package bancoproyecto.models;

import java.util.List;

public record User(String name, String user, List<Account> accounts) {
    public User(String name, String user) {
        this(name, user, null);
    }

    public User(String name, String user, List<Account> accounts) {
        this.name = name;
        this.user = user;
        this.accounts = accounts;
    }
}
