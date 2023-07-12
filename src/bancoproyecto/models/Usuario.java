package bancoproyecto.models;

public record Usuario(String name, String username, String password) {
    @Override
    public String toString() {
        return String.format("Name: %s, Username: %s, Password: %s", name, username, password);
    }
}
