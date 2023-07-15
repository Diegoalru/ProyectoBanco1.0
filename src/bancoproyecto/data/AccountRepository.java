package bancoproyecto.data;

import bancoproyecto.data.models.AccountModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class AccountRepository {
    private static final Logger logger = Logger.getLogger(AccountRepository.class.getName());
    private static final Database database = new Database();

    /**
     * Obtiene la lista de cuentas que se encuentran en {@code Accounts}
     *
     * @param uuid {@link String} que representa el UUID del usuario.
     * @return Lista de {@link AccountModel cuentas}
     */
    public static CompletableFuture<List<AccountModel>> GetAccountsByUserUUID(String uuid) {
        return database.GetConnectionAsync()
                .thenComposeAsync(connection -> {
                    try {
                        var script = "SELECT * FROM ACCOUNTS WHERE USER_ID = ?";
                        var statement = connection.prepareStatement(script);

                        statement.setString(1, uuid);

                        var resultSet = statement.executeQuery();
                        var accounts = new ArrayList<AccountModel>();

                        while (resultSet.next()) {
                            var account = new AccountModel(
                                    resultSet.getString("UUID"),
                                    resultSet.getString("DESCRIPTION"),
                                    resultSet.getDouble("BALANCE")
                            );
                            accounts.add(account);
                        }

                        return CompletableFuture.completedFuture(accounts);
                    } catch (Exception e) {
                        logger.severe("Failed to get accounts! Error: " + e.getMessage());
                        throw new RuntimeException("Failed to get accounts!");
                    } finally {
                        database.CloseConnectionAsync(connection);
                    }
                });
    }

}
