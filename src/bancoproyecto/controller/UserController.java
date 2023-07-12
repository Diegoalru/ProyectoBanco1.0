package bancoproyecto.controller;

import bancoproyecto.data.UserRepository;
import bancoproyecto.data.models.UserModel;
import bancoproyecto.models.Account;
import bancoproyecto.models.User;
import bancoproyecto.models.UserLogin;
import bancoproyecto.models.UserRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserController {
    private final int STR_MIN_LENGTH = 5;

    /**
     * Registra un usuario en el sistema.
     *
     * @param usuario Usuario que se desea registrar
     * @throws Exception Si el usuario no cumple con las validaciones
     */
    public void RegistraUsuario(UserRegister usuario) throws Exception {
        //#region Validaciones
        validar(usuario.username(), "El usuario debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH),
                s -> s.length() >= STR_MIN_LENGTH);
        validar(usuario.username(), "El usuario no puede contener caracteres especiales",
                s -> s.matches("[a-zA-Z0-9]+"));
        validar(usuario.password(), "La contraseña debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH),
                s -> s.length() >= STR_MIN_LENGTH);
        validar(usuario.password(), "La contraseña no puede ser igual al usuario",
                s -> !s.equals(usuario.username()));
        validar(usuario.password(), "La contrasena no puede tener caracteres iguales consecutivos",
                s -> !s.matches(".*([a-zA-Z])\\1+.*"));
        validar(usuario.password(), "La contraseña solo puede tener los siguientes caracteres especiales: $, #, %, &, *",
                s -> s.matches("^[a-zA-Z0-9$#%&*]+$"));
        //#endregion

        UserModel usuarioModel = new UserModel(usuario.name(), usuario.username(), usuario.password());

        if (UserRepository.UserExists(usuarioModel)) {
            throw new Exception("El usuario ya existe");
        }

        UserRepository.NewUser(usuarioModel);
    }

    /**
     * Inicia sesion con el usuario que se encuentra en UsuarioData
     *
     * @param usuario Usuario que se desea iniciar sesion
     * @throws Exception Si el usuario no cumple con las validaciones
     */
    public void InicioSesion(UserLogin usuario) throws Exception {
        //#region Validaciones
        validar(usuario.username(), "El usuario debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH),
                s -> s.length() >= STR_MIN_LENGTH);
        validar(usuario.username(), "El usuario no puede contener caracteres especiales",
                s -> s.matches("[a-zA-Z0-9]+"));
        validar(usuario.password(), "La contraseña debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH),
                s -> s.length() >= STR_MIN_LENGTH);
        validar(usuario.password(), "La contraseña no puede ser igual al usuario",
                s -> !s.equals(usuario.username()));
        validar(usuario.password(), "La contrasena no puede tener caracteres iguales consecutivos",
                s -> !s.matches(".*([a-zA-Z])\\1+.*"));
        validar(usuario.password(), "La contraseña solo puede tener los siguientes caracteres especiales: $, #, %, &, *",
                s -> s.matches("^[a-zA-Z0-9$#%&*]+$"));
        //#endregion

        UserModel usuarioModel = new UserModel(usuario.username(), usuario.password());
        UserModel resultLogin = UserRepository.Login(usuarioModel);

        if (resultLogin == null) {
            throw new Exception("Username or password incorrect!");
        }

        List<Account> accounts = new ArrayList<>();
        for (var item : resultLogin.getCuentas()) {
            accounts.add(new Account(item.getGUID(), item.getName(), item.getBalance()));
        }

        User user = new User(resultLogin.getUsuario(), accounts);
        MainController.setUsuario(user);
    }

    /**
     * Lista de usuarios en UsuarioData
     *
     * @return Usuario que se encuentra en UsuarioData
     * @deprecated Solo para pruebas
     */
    public String ObtieneListaUsuarios() {
        var users = UserRepository.GetUsers().toString();
        System.out.println(users);
        return users;
    }

    private void validar(String value, String errorMessage, Predicate<String> condition) throws Exception {
        if (!condition.test(value)) {
            throw new Exception(errorMessage);
        }
    }
}
