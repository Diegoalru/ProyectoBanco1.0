package bancoproyecto;

public class UsuarioData {
    private static Usuario UsuarioSistema;
    
    public static Usuario GetUsuario(){
        return UsuarioSistema;
    }
    
    public static void SetUsuario(Usuario nuevoUsuario){
        UsuarioSistema = nuevoUsuario;
    }
    
    public static Boolean InicioSesion(Usuario usuario){
        if(usuario.getUsuario().equals(UsuarioSistema.getUsuario()) &&
           usuario.getContraseña().equals(UsuarioSistema.getContraseña())){
            return true;
        }
        return false;
    }
}
