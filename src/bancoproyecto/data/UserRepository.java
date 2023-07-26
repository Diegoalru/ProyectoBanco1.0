package bancoproyecto.data;

import bancoproyecto.data.models.UserModel;

import java.sql.Types;
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
    public static CompletableFuture<List<UserModel>> getUsers() {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "SELECT BKMUID, BKMNAM, BKMUSN FROM BKMUSR";
                        var statement = connection.prepareStatement(script);
                        var resultSet = statement.executeQuery();

                        var users = new ArrayList<UserModel>();

                        while (resultSet.next()) {
                            var user = new UserModel(
                                    resultSet.getString("BKMUID"),
                                    resultSet.getString("BKMNAM"),
                                    resultSet.getString("BKMUSN")
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
    public static CompletableFuture<Integer> newUser(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "{call SP_ALTA_BKMUSR(?, ?, ?, ?, ?)}";
                        var callableStatement = connection.prepareCall(script);

                        callableStatement.setString(1, user.getName());
                        callableStatement.setString(2, user.getUsername());
                        callableStatement.setString(3, user.getPassword());

                        callableStatement.registerOutParameter(4, Types.INTEGER);
                        callableStatement.registerOutParameter(5, Types.VARCHAR);

                        callableStatement.execute();
                        var result = callableStatement.getInt(4);

                        return CompletableFuture.completedFuture(result);
                    } catch (Exception e) {
                        logger.severe("Failed to create user! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to create user");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Inicia sesión con credenciales proporcionadas por el usuario.
     *
     * @param user {@link UserModel Usuario} que se desea validar.
     * @return {@link Boolean} que indica si el usuario existe.
     */
    public static CompletableFuture<Integer> login(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "{? = call FN_INICIO_SESION(?, ?)}";
                        var callableStatement = connection.prepareCall(script);

                        callableStatement.registerOutParameter(1, Types.INTEGER);
                        callableStatement.setString(2, user.getUsername());
                        callableStatement.setString(3, user.getPassword());

                        callableStatement.execute();

                        var result = callableStatement.getInt(1);
                        return CompletableFuture.completedFuture(result);
                    } catch (Exception e) {
                        logger.severe("Failed to Login! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to Login");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Obtiene la información del usuario que inició sesión.
     *
     * @param user {@link UserModel Usuario} que inició sesión.
     * @return {@link UserModel Usuario} datos del usuario.
     */
    public static CompletableFuture<UserModel> getUser(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "{? = call FN_OBTIENE_USUARIO(?)}";
                        var callableStatement = connection.prepareCall(script);

                        callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                        callableStatement.setString(2, user.getUsername());

                        callableStatement.execute();

                        var resultSet = (java.sql.ResultSet) callableStatement.getObject(1);

                        if (resultSet.next()) {
                            return CompletableFuture.completedFuture(new UserModel(
                                    resultSet.getString("BKMUID"),
                                    resultSet.getString("BKMNAM"),
                                    resultSet.getString("BKMUSN"),
                                    resultSet.getString("BKMPWD")
                            ));
                        }

                        return CompletableFuture.completedFuture(null);
                    } catch (Exception e) {
                        logger.severe("Failed to get user data! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to get user data!");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

    /**
     * Verifica si el usuario ya existe en el sistema.
     *
     * @param user {@link UserModel Usuario} que se desea verificar.
     * @return {@link Boolean} que indica si el usuario existe o no.
     */
    public static CompletableFuture<Boolean> userExists(UserModel user) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "{? = call FN_VERIFICA_USUARIO(?)}";
                        var callableStatement = connection.prepareCall(script);

                        callableStatement.registerOutParameter(1, Types.INTEGER);
                        callableStatement.setString(2, user.getUsername());

                        callableStatement.execute();

                        int result = callableStatement.getInt(1);
                        boolean userExists = result == 1;

                        return CompletableFuture.completedFuture(userExists);
                    } catch (Exception e) {
                        logger.severe("Failed to check if user exists! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to check if user exists!");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }
}
