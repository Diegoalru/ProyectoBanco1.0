package bancoproyecto.models;

public record UserLogin(String username, String password) {
    public UserLogin {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username can't be blank");
        }

        if (password.isBlank()) {
            throw new IllegalArgumentException("Password can't be blank");
        }
    }

    @Override
    public String toString() {
        return String.format("Username: %s, Password: %s", username, password);
    }
}
