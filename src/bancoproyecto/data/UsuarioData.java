package bancoproyecto.data;

import bancoproyecto.data.models.UsuarioModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioData {

    /**
     * Objeto de tipo Usuario que se verificará en el inicio de sesion.
     */
    private static final List<UsuarioModel> UsuariosDelSistema = new ArrayList<>();

    /**
     * Obtiene el usuario que se encuentra en UsuarioData
     * @return Usuario que se encuentra en UsuarioData
     */
    public static List<UsuarioModel> ObtieneListaUsuarios(){
        return UsuariosDelSistema;
    }

    /**
     * Guarda uno o varios usuarios en UsuarioData.
     *
     * @param nuevoUsuario Usuario que se desea guardar
     */
    public static void NuevoUsuario(UsuarioModel ... nuevoUsuario){
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
    public static UsuarioModel InicioSesion(UsuarioModel usuario){
        for (var usuarioSistema : UsuariosDelSistema) {
            if (usuarioSistema.getUsuario().equals(usuario.getUsuario()) &&
                    usuarioSistema.getContrasena().equals(usuario.getContrasena())) {
                return usuarioSistema;
            }
        }
        return null;
    }

    /**
     * Verifica si el usuario que se desea registrar ya existe en UsuarioData
     * @param usuario Usuario que se desea verificar
     * @return True si el usuario ya existe en UsuarioData
     */
    public static Boolean UsuarioExiste(UsuarioModel usuario) {
        for (var usuarioSistema : UsuariosDelSistema) {
            if (usuarioSistema.getUsuario().equals(usuario.getUsuario())) {
                return true;
            }
        }
        return false;
    }
}
