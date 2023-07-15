package bancoproyecto.models;

import java.util.List;

public record User(String user, List<Account> accounts) {
    public User(String user) {
        this(user, null);
    }

    public User(String user, List<Account> accounts) {
        this.user = user;
        this.accounts = accounts;
    }
}
