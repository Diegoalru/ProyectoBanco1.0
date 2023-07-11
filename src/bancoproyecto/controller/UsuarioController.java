package bancoproyecto.controller;

import bancoproyecto.data.UsuarioData;
import bancoproyecto.data.models.UsuarioModel;
import bancoproyecto.models.Usuario;

import java.util.logging.Logger;


public class UsuarioController {
    private final int STR_MIN_LENGTH = 5;

    /**
     * Registra un usuario en el sistema.
     * @param usuario Usuario que se desea registrar
     * @throws Exception Si el usuario no cumple con las validaciones
     */
    public void RegistraUsuario(Usuario usuario) throws Exception{
        //#region Validaciones
        if (usuario.getUsuario().length() < STR_MIN_LENGTH) {
            throw new Exception("El usuario debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (usuario.getContrasena().length() < STR_MIN_LENGTH) {
            throw new Exception("La contraseña debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (!usuario.getUsuario().matches("[a-zA-Z0-9]+")) {
            throw new Exception("El usuario no puede contener caracteres especiales");
        }

        if (usuario.getContrasena().equals(usuario.getUsuario())) {
            throw new Exception("La contraseña no puede ser igual al usuario");
        }

        if (usuario.getContrasena().matches(".*([a-zA-Z])\\1+.*")) {
            throw new Exception("La contrasena no puede tener caracteres iguales consecutivos");
        }
        //#endregion

        UsuarioModel usuarioModel = new UsuarioModel(usuario.getUsuario(), usuario.getContrasena());

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
        if (usuario.getUsuario().length() < STR_MIN_LENGTH) {
            throw new Exception("El usuario debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (usuario.getContrasena().length() < STR_MIN_LENGTH) {
            throw new Exception("La contraseña debe tener al menos %d caracteres".formatted(STR_MIN_LENGTH));
        }

        if (!usuario.getUsuario().matches("[a-zA-Z0-9]+")) {
            throw new Exception("El usuario no puede contener caracteres especiales");
        }

        if (usuario.getContrasena().equals(usuario.getUsuario())) {
            throw new Exception("La contraseña no puede ser igual al usuario");
        }

        if (usuario.getContrasena().matches(".*([a-zA-Z])\\1+.*")) {
            throw new Exception("La contrasena no puede tener caracteres iguales consecutivos");
        }
        //#endregion

        UsuarioModel usuarioModel = new UsuarioModel(usuario.getUsuario(), usuario.getContrasena());
        UsuarioModel resultado = UsuarioData.InicioSesion(usuarioModel);

        if (resultado == null) {
            throw new Exception("El usuario no existe");
        }

        return new Usuario(resultado.getUsuario(), resultado.getContrasena());
    }

    /**
     * Lista de usuarios en UsuarioData
     * @return Usuario que se encuentra en UsuarioData
     * @deprecated Solo para pruebas
     */
    @Deprecated(forRemoval = false)
    public String ObtieneListaUsuarios() {
        var users = UsuarioData.ObtieneListaUsuarios().toString();
        System.out.println(users);
        return users;
    }
}
