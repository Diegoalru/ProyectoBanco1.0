package bancoproyecto.controller;

import bancoproyecto.data.UsuarioData;
import bancoproyecto.data.models.UsuarioModel;
import bancoproyecto.models.Usuario;

public class UsuarioController {
    private final int STR_MIN_LENGTH = 5;

    /**
     * Registra un usuario en el sistema.
     * @param usuario Usuario que se desea registrar
     * @throws Exception Si el usuario no cumple con las validaciones
     */
    public void RegistraUsuario(Usuario usuario) throws Exception{
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

        UsuarioModel usuarioModel = new UsuarioModel(usuario.name(), usuario.username(), usuario.password());

        if (UsuarioData.UsuarioExiste(usuarioModel)) {
            throw new Exception("El usuario ya existe");
        }

        UsuarioData.NuevoUsuario(usuarioModel);
    }

    /**
     * Inicia sesion con el usuario que se encuentra en UsuarioData
     * @param usuario Usuario que se desea iniciar sesion
     * @return True si el usuario coincide con el usuario en UsuarioData
     * @throws Exception Si el usuario no cumple con las validaciones
     */
    public Usuario InicioSesion(Usuario usuario) throws Exception {
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

        UsuarioModel usuarioModel = new UsuarioModel(usuario.name(), usuario.username(), usuario.password());
        UsuarioModel resultado = UsuarioData.InicioSesion(usuarioModel);

        if (resultado == null) {
            throw new Exception("El usuario o la contraseña son incorrectos");
        }

        return new Usuario(resultado.getNombre(), resultado.getUsuario(), resultado.getContrasena());
    }

    /**
     * Lista de usuarios en UsuarioData
     * @return Usuario que se encuentra en UsuarioData
     * @deprecated Solo para pruebas
     */
    @Deprecated
    public String ObtieneListaUsuarios() {
        var users = UsuarioData.ObtieneListaUsuarios().toString();
        System.out.println(users);
        return users;
    }
}
