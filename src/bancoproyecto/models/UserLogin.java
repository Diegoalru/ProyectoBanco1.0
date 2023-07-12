package bancoproyecto.models;

public record UserLogin(String username, String password) {
    @Override
    public String toString() {
        return String.format("Username: %s, Password: %s", username, password);
    }
}
