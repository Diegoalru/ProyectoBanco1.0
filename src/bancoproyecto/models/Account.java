package bancoproyecto.models;

public record Account(String uuid, String description, Double balance) {
    public Account(String description, Double balance) {
        this(null, description, balance);
    }
}
