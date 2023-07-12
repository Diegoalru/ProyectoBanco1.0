package bancoproyecto.controller;

import bancoproyecto.data.UserRepository;
import bancoproyecto.data.models.UserModel;
import bancoproyecto.models.Account;
import bancoproyecto.models.User;
import bancoproyecto.models.UserLogin;
import bancoproyecto.models.UserRegister;

import java.util.ArrayList;
import java.util.List;

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
        if (usuario.username().length() < STR_MIN_LENGTH) {
            throw new Exception("El usuario debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (!usuario.username().matches("[a-zA-Z0-9]+")) {
            throw new Exception("El usuario no puede contener caracteres especiales");
        }

        if (usuario.password().length() < STR_MIN_LENGTH) {
            throw new Exception("La contraseña debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (usuario.password().equals(usuario.username())) {
            throw new Exception("La contraseña no puede ser igual al usuario");
        }

        if (usuario.password().matches(".*([a-zA-Z])\\1+.*")) {
            throw new Exception("La contrasena no puede tener caracteres iguales consecutivos");
        }

        // La contraseña puede tener caracteres especiales pero no espacios y solo $, #, %, &, *.
        if (!usuario.password().matches("^[a-zA-Z0-9$#%&*]+$")) {
            throw new Exception("La contraseña solo puede tener los siguientes caracteres especiales: $, #, %, &, *");
        }
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
        if (usuario.username().length() < STR_MIN_LENGTH) {
            throw new Exception("El usuario debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (!usuario.username().matches("[a-zA-Z0-9]+")) {
            throw new Exception("El usuario no puede contener caracteres especiales");
        }

        if (usuario.password().length() < STR_MIN_LENGTH) {
            throw new Exception("La contraseña debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (usuario.password().equals(usuario.username())) {
            throw new Exception("La contraseña no puede ser igual al usuario");
        }

        if (usuario.password().matches(".*([a-zA-Z])\\1+.*")) {
            throw new Exception("La contrasena no puede tener caracteres iguales consecutivos");
        }

        // La contraseña puede tener caracteres especiales pero no espacios y solo $, #, %, &, *.
        if (!usuario.password().matches("^[a-zA-Z0-9$#%&*]+$")) {
            throw new Exception("La contraseña solo puede tener los siguientes caracteres especiales: $, #, %, &, *");
        }
        //#endregion

        UserModel usuarioModel = new UserModel(usuario.username(), usuario.password());
        UserModel resultLogin = UserRepository.Login(usuarioModel);

        if (resultLogin == null) {
            throw new Exception("El usuario o la contraseña son incorrectos");
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
     * @return Usuario que se encuentra en UsuarioData
     * @deprecated Solo para pruebas
     */
    public String ObtieneListaUsuarios() {
        var users = UserRepository.GetUsers().toString();
        System.out.println(users);
        return users;
    }
}
