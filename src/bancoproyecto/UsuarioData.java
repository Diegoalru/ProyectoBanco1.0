package bancoproyecto;

public class UsuarioData {

    /**
     * Objeto de tipo Usuario que se verificará en el inicio de sesion.
     */
    private static Usuario UsuarioSistema;

    /**
     * Obtiene el usuario que se encuentra en UsuarioData
     * @return Usuario que se encuentra en UsuarioData
     */
    public static Usuario GetUsuario(){
        return UsuarioSistema;
    }

    /**
     * Guarda el usuario en la clase UsuarioData
     * @param nuevoUsuario Usuario que se desea guardar
     */
    public static void SetUsuario(Usuario nuevoUsuario){
        UsuarioSistema = nuevoUsuario;
    }

    /**
     * Inicia sesion con el usuario que se encuentra en UsuarioData
     * @param usuario Usuario que se desea iniciar sesion
     * @return True si el usuario coincide con el usuario en UsuarioData
     */
    public static Boolean InicioSesion(Usuario usuario){
        return usuario.getUsuario().equals(UsuarioSistema.getUsuario()) &&
                usuario.getContrasena().equals(UsuarioSistema.getContrasena());
    }
}
