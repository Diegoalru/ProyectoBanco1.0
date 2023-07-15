package bancoproyecto.controller;

import bancoproyecto.data.AccountRepository;
import bancoproyecto.data.UserRepository;
import bancoproyecto.data.models.UserModel;
import bancoproyecto.models.Account;
import bancoproyecto.models.User;
import bancoproyecto.models.UserLogin;
import bancoproyecto.models.UserRegister;
import bancoproyecto.utils.PasswordUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class UserController {

    private final ResourceBundle bundle = ResourceBundle.getBundle("bancoproyecto.resources.Strings");
    private final int STR_MIN_LENGTH = 5;
    private final int STR_MAX_LENGTH = 32;

    /**
     * Registra un user en el sistema.
     *
     * @param user {@link UserRegister Usuario} que se desea registrar.
     * @throws Exception Si el {@link UserRegister user} no cumple con las validaciones.
     */
    public void RegisterUser(UserRegister user) throws Exception {
        //#region Validaciones
        validate(user.name(), MessageFormat.format(bundle.getString("name_min_length"), 3), s -> s.length() >= 3);
        validate(user.name(), MessageFormat.format(bundle.getString("name_max_length"), STR_MAX_LENGTH), s -> s.length() <= STR_MAX_LENGTH);
        validate(user.name(), bundle.getString("name_invalid_characters"), s -> s.matches("^[a-zA-ZáéíóúÁÉÍÓÚ ]+$"));
        validate(user.username(), MessageFormat.format(bundle.getString("username_min_length"), STR_MIN_LENGTH), s -> s.length() >= STR_MIN_LENGTH);
        validate(user.username(), MessageFormat.format(bundle.getString("username_max_length"), STR_MAX_LENGTH), s -> s.length() <= STR_MAX_LENGTH);
        validate(user.username(), bundle.getString("username_invalid_characters"), s -> s.matches("[a-zA-Z0-9]+"));
        validate(user.password(), MessageFormat.format(bundle.getString("password_min_length"), STR_MIN_LENGTH), s -> s.length() >= STR_MIN_LENGTH);
        validate(user.password(), MessageFormat.format(bundle.getString("password_max_length"), STR_MAX_LENGTH), s -> s.length() <= STR_MAX_LENGTH);
        validate(user.password(), bundle.getString("password_cannot_be_equal_to_username"), s -> !s.equals(user.username()));
        validate(user.password(), bundle.getString("password_cannot_have_consecutive_equal_characters"), s -> !s.matches(".*([a-zA-Z])\\1+.*"));
        validate(user.password(), bundle.getString("password_invalid_characters"), s -> s.matches("^[a-zA-Z0-9$#%&*_]+$"));
        //#endregion

        UserModel usuarioModel = new UserModel(user.name(), user.username(), PasswordUtils.HashPassword(user.password()));

        if (UserRepository.UserExists(usuarioModel).get()) {
            throw new Exception(bundle.getString("register_username_taken"));
        }

        if (!UserRepository.NewUser(usuarioModel).get()) {
            throw new Exception(bundle.getString("register_error"));
        }
    }

    /**
     * Inicia sesion con el usuario proporcionado.
     *
     * @param user {@link UserLogin Usuario} que se desea iniciar sesion
     * @throws Exception Si el {@link UserLogin usuario} no cumple con las validaciones
     */
    public void LoginUser(UserLogin user) throws Exception {
        //#region Validaciones
        validate(user.username(), MessageFormat.format(bundle.getString("username_min_length"), STR_MIN_LENGTH), s -> s.length() >= STR_MIN_LENGTH);
        validate(user.username(), MessageFormat.format(bundle.getString("username_max_length"), STR_MAX_LENGTH), s -> s.length() <= STR_MAX_LENGTH);
        validate(user.username(), bundle.getString("username_invalid_characters"), s -> s.matches("[a-zA-Z0-9]+"));
        validate(user.password(), MessageFormat.format(bundle.getString("password_min_length"), STR_MIN_LENGTH), s -> s.length() >= STR_MIN_LENGTH);
        validate(user.password(), bundle.getString("password_cannot_have_consecutive_equal_characters"), s -> !s.matches(".*([a-zA-Z])\\1+.*"));
        validate(user.password(), bundle.getString("password_invalid_characters"), s -> s.matches("^[a-zA-Z0-9$#%&*]+$"));
        //#endregion

        UserModel userModel = new UserModel(user.username(), PasswordUtils.HashPassword(user.password()));

        AtomicReference<UserModel> resultLogin = new AtomicReference<>();
        var validatePassword = UserRepository.ValidatePassword(userModel)
                .thenAcceptAsync(result -> {
                    if (result == 1) {
                        resultLogin.set(UserRepository.Login(userModel).join());
                    }
                });

        validatePassword.join();

        if (resultLogin.get() == null) {
            throw new Exception(bundle.getString("login_invalid_credentials"));
        }

        var accounts = new ArrayList<Account>();
        for (var item : AccountRepository.GetAccountsByUserUUID(resultLogin.get().getUUID().toString()).get()) {
            accounts.add(new Account(item.getUUID().toString(), item.getDescription(), item.getBalance()));
        }

        MainController.setUsuario(new User(resultLogin.get().getUsername(), accounts));
    }

    /**
     * Lista de usuarios en el sistema.
     *
     * @return Lista de {@link User usuarios} en el sistema.
     */
    public List<User> GetUserList() {
        var users = new ArrayList<User>();
        for (var item : UserRepository.GetUsers().join()) {
            var accounts = new ArrayList<Account>();
            users.add(new User(item.getUsername()));
        }

        return users;
    }

    /**
     * Válida una condición y lanza una excepción si no se cumple.
     *
     * @param value        Valor a validar.
     * @param errorMessage Mensaje de error a mostrar.
     * @param condition    Condición a validar.
     * @throws Exception   Si la condición no se cumple.
     */
    private void validate(String value, String errorMessage, Predicate<String> condition) throws Exception {
        if (!condition.test(value)) {
            throw new Exception(errorMessage);
        }
    }
}
