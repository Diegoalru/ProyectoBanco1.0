package bancoproyecto.data.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserModel {
    public UUID uuid;
    private String name;
    private String username;
    private String password;
    private List<AccountModel> accounts;


    /**
     * Constructor usado para iniciar sesión.
     *
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     */
    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor usado para crear un nuevo usuario.
     * @param name Nombre del usuario
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     */
    public UserModel(String name, String username, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accountsList) {
        accounts = accountsList;
    }

    @Override
    public String toString() {
        return String.format("UUID: %s, Name: %s, and Username: %s", uuid, name, username); // %s es para string.
    }
}
