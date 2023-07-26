package bancoproyecto.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import static java.sql.DriverManager.deregisterDriver;
import static java.sql.DriverManager.registerDriver;

public class Database {
    private static final Logger logger = Logger.getLogger(Database.class.getName());

    public CompletableFuture<Connection> GetConnectionAsync()  {
        return CompletableFuture.supplyAsync(() -> {
            try {
                registerDriver(new oracle.jdbc.OracleDriver());

                var databaseProperties = new Properties();
                var fileInputStream = new FileInputStream("src/bancoproyecto/resources/database.properties");
                databaseProperties.load(fileInputStream);

                var jdbcProperties = new Properties();
                jdbcProperties.put("v$session.program", "BancoProyecto");
                jdbcProperties.put("user", databaseProperties.getProperty("db_username"));
                jdbcProperties.put("password", databaseProperties.getProperty("db_password"));
                jdbcProperties.put("oracle.net.tns_admin", databaseProperties.getProperty("db_tns"));

                // Set timeout to 5 seconds
                jdbcProperties.put("oracle.jdbc.ReadTimeout", 5000);
                jdbcProperties.put("oracle.net.CONNECT_TIMEOUT", 5000);

                return DriverManager.getConnection(databaseProperties.getProperty("db_url"), jdbcProperties);
            } catch (SQLException e) {
                logger.severe("Failed to get database connection! Error: " + e.getMessage());
                throw new RuntimeException("Failed to get database connection", e);
            } catch (IOException e) {
                logger.severe("Failed to load database properties! Error: " + e.getMessage());
                throw new RuntimeException("Failed to load database properties", e);
            }
        });
    }

    public CompletableFuture<Void> CloseConnectionAsync(Connection connection) {
        return CompletableFuture.runAsync(() -> {
            try {
                connection.close();
                deregisterDriver(new oracle.jdbc.OracleDriver());
            } catch (SQLException e) {
                logger.severe("Failed to close database connection! Error: " + e.getMessage());
                throw new RuntimeException("Failed to close database connection", e);
            }
        });
    }

    public CompletableFuture<String> GetVersionAsync() {
        return GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        if (connection.isClosed()) {
                            logger.severe("¡No se pudo conectar a la base de datos!");
                            return CompletableFuture.completedFuture(null);
                        }

                        logger.info("¡Conexión exitosa!");

                        // Crear una consulta
                        var statement = connection.createStatement();
                        var resultSet = statement.executeQuery("SELECT BANNER AS VERSION FROM V$VERSION");

                        // Imprimir los resultados
                        if (resultSet.next()) {
                            return CompletableFuture.completedFuture(resultSet.getString("VERSION"));
                        }
                    } catch (SQLException e) {
                        logger.severe(e.getMessage());
                    } finally {
                        logger.info("Desconectado de la base de datos.");
                        CloseConnectionAsync(connection).join();
                    }
                    return CompletableFuture.completedFuture(null);
                });
    }
}
