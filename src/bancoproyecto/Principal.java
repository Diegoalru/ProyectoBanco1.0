package bancoproyecto;

import java.util.logging.Logger;

/**
 *
 * @author DuchetCR
 */
public class Principal {

    private static final Logger logger = Logger.getLogger(Principal.class.getName());

    public static void main(String[] args) {
        Start();
    }

    private static void Start() {
        // Crea un usuario manualmente.
        Usuario nuevoUsuario = UsuarioControlador.CreaUsuario();

        // Crea un usuario automáticamente.
        Usuario nuevoUsuario1 = new Usuario("user1", "123456789");
        Usuario nuevoUsuario2 = new Usuario("user2", "123456789");

        // Guarda los usuarios en UsuarioData.
        UsuarioData.NuevoUsuario(nuevoUsuario, nuevoUsuario1, nuevoUsuario2);

        // Verifica los usuarios registrados en UsuarioData.
        logger.info("Usuarios registrados en el sistema: " + UsuarioData.ObtieneListaUsuarios());

        UsuarioControlador.IniciarSesionUsuario();
    }
}
