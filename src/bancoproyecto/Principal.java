package bancoproyecto;

import bancoproyecto.data.UsuarioData;
import bancoproyecto.models.Usuario;
import bancoproyecto.view.InicioView;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    /**
     * Logger para la clase Principal.
     */
    private static final Logger logger = Logger.getLogger(Principal.class.getName());

    public static void main(String[] args) {
        Start();
    }

    /**
     * Método que inicia la interfaz GUI.
     */
    private static void Start() {
        logger.log(Level.INFO, "Iniciando programa");

        InsertaUsuariosDePrueba();

        SwingUtilities.invokeLater(() -> {
            InicioView inicioView = new InicioView();
            inicioView.setVisible(true);
        });
    }

    private static void InsertaUsuariosDePrueba() {
        // Crea un usuario automáticamente.
        Usuario nuevoUsuario1 = new Usuario("user1", "123456789");
        Usuario nuevoUsuario2 = new Usuario("user2", "123456789");

        // Guarda los usuarios en UsuarioData.
        UsuarioData.NuevoUsuario(nuevoUsuario1, nuevoUsuario2);

        logger.log(Level.INFO, "Usuarios registrados en el sistema: {0}", UsuarioData.ObtieneListaUsuarios());
    }
}
