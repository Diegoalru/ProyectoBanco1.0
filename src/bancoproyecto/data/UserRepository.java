package bancoproyecto.data;

import bancoproyecto.data.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class UserRepository {

    private static final Database database = new Database();
    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());

    /**
     * Obtiene la lista de usuarios que se encuentran en {@code Users}
     *
     * @return Lista de {@link UserModel usuarios}
     */
    public static CompletableFuture<List<UserModel>> GetUsers() {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "SELECT * FROM USERS";
                        var statement = connection.prepareStatement(script);
                        var resultSet = statement.executeQuery();

                        var users = new ArrayList<UserModel>();

                        while (resultSet.next()) {
                            var user = new UserModel(
                                    resultSet.getString("UUID"),
                                    resultSet.getString("NAME"),
                                    resultSet.getString("USERNAME"),
                                    resultSet.getString("PASSWORD")
                            );
                            users.add(user);
                        }

                        return CompletableFuture.completedFuture(users);
                    } catch (Exception e) {
                        logger.severe("Failed to get users! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to get users");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Guarda el {@link UserModel usuario} en la base de datos.
     *
     * @param user {@link UserModel Usuario} que se desea guardar.
     * @return {@link Boolean} que indica si el usuario se guardó correctamente.
     */
    public static CompletableFuture<Boolean> NewUser(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "INSERT INTO USERS (UUID, NAME, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
                        var statement = connection.prepareStatement(script);

                        statement.setString(1, user.getUUID().toString());
                        statement.setString(2, user.getName());
                        statement.setString(3, user.getUsername());
                        statement.setString(4, user.getPassword());

                        var result = statement.executeUpdate();

                        return CompletableFuture.completedFuture(result > 0);
                    } catch (Exception e) {
                        logger.severe("Failed to create user! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to create user");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Válida que el usuario exista en la base de datos.
     *
     * @param user {@link UserModel Usuario} que se desea validar.
     * @return {@link Boolean} que indica si el usuario existe.
     */
    public static CompletableFuture<Integer> ValidatePassword(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "SELECT password_validator(?, ?) FROM DUAL";
                        var statement = connection.prepareStatement(script);

                        statement.setString(1, user.getUsername());
                        statement.setString(2, user.getPassword());

                        var resultSet = statement.executeQuery();

                        resultSet.next();
                        return CompletableFuture.completedFuture(resultSet.getInt(1));
                    } catch (Exception e) {
                        logger.severe("Failed to validate password! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to validate password");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Inicia sesión con credenciales proporcionadas por el usuario.
     *
     * @param user {@link UserModel Usuario} que se desea iniciar sesión
     * @return {@link UserModel Usuario} que inició sesión.
     */
    public static CompletableFuture<UserModel> Login(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
                        var statement = connection.prepareStatement(script);

                        statement.setString(1, user.getUsername());
                        statement.setString(2, user.getPassword());

                        var resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            return CompletableFuture.completedFuture(new UserModel(
                                    resultSet.getString("NAME"),
                                    resultSet.getString("USERNAME"),
                                    resultSet.getString("PASSWORD")
                            ));
                        }

                        return CompletableFuture.completedFuture(null);
                    } catch (Exception e) {
                        logger.severe("Failed to login! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to login!");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Verifica si el usuario que se desea registrar ya existe en el sistema.
     *
     * @param user {@link UserModel Usuario} que se desea verificar.
     * @return {@link Boolean} que indica si el usuario existe o no.
     */
    public static CompletableFuture<Boolean> UserExists(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "SELECT * FROM USERS WHERE USERNAME = ?";
                        var statement = connection.prepareStatement(script);

                        statement.setString(1, user.getUsername());

                        var resultSet = statement.executeQuery();

                        return CompletableFuture.completedFuture(resultSet.next());
                    } catch (Exception e) {
                        logger.severe("Failed to check if user exists! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to check if user exists!");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }
}
