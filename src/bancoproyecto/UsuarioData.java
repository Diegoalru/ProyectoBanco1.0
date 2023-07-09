package bancoproyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioData {

    /**
     * Objeto de tipo Usuario que se verificará en el inicio de sesion.
     */
    private static final List<Usuario> UsuariosDelSistema = new ArrayList<>();

    /**
     * Obtiene el usuario que se encuentra en UsuarioData
     * @return Usuario que se encuentra en UsuarioData
     */
    public static List<Usuario> ObtieneListaUsuarios(){
        return UsuariosDelSistema;
    }

    /**
     * Guarda el usuario en la clase UsuarioData
     * @param nuevoUsuario Usuario que se desea guardar
     */
    public static void NuevoUsuario(Usuario ... nuevoUsuario){
        try{
            Collections.addAll(UsuariosDelSistema, nuevoUsuario);
        } catch(NullPointerException e){
            System.err.println("Error no se pudo guardar el usuario");
        } catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Inicia sesion con el usuario que se encuentra en UsuarioData
     * @param usuario Usuario que se desea iniciar sesion
     * @return True si el usuario coincide con el usuario en UsuarioData
     */
    public static Boolean InicioSesion(Usuario usuario){
        for (Usuario usuarioSistema : UsuariosDelSistema) {
            if (usuarioSistema.getUsuario().equals(usuario.getUsuario()) &&
                    usuarioSistema.getContrasena().equals(usuario.getContrasena())) {
                return true;
            }
        }
        return false;
    }
}
