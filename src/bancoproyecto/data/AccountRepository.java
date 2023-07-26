package bancoproyecto.data;

import bancoproyecto.data.models.AccountModel;

import java.sql.ResultSet;
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
                        var script = "{? = call OBTIENE_CUENTAS_USUARIO(?)}";
                        var callableStatement = connection.prepareCall(script);

                        callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                        callableStatement.setString(2, uuid);

                        callableStatement.execute();

                        var resultSet = (ResultSet) callableStatement.getObject(1);
                        var accounts = new ArrayList<AccountModel>();

                        while (resultSet.next()) {
                            var account = new AccountModel(
                                    resultSet.getString("BKMUID"),
                                    resultSet.getInt("BKMIDU"),
                                    resultSet.getString("BKMDES"),
                                    resultSet.getDouble("BKMBAL"),
                                    resultSet.getInt("BKMSTA")
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
