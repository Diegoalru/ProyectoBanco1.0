package bancoproyecto;

/**
 *
 * @author DuchetCR
 */
public class Principal {

    public static void main(String[] args) {
        Start();
    }

    private static void Start() {
        // Crear un usuario
        Usuario nuevoUsuario = UsuarioControlador.CreaUsuario();

        // Guardar el usuario en la clase UsuarioData
        UsuarioData.SetUsuario(nuevoUsuario);

        // Iniciar sesion.
        UsuarioControlador.IniciarSesionUsuario();
    }
}
