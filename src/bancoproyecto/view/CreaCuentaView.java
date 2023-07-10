package bancoproyecto.view;

import bancoproyecto.controller.UsuarioController;
import bancoproyecto.models.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;

import static bancoproyecto.view.CredencialesUtils.SolicitaContrasena;
import static bancoproyecto.view.CredencialesUtils.SolicitaUsuario;

public class CreaCuentaView {

    private final UsuarioController controller = new UsuarioController();
    private final Logger logger = Logger.getLogger(CreaCuentaView.class.getName());

    /**
     * Crea un usuario con los datos ingresados por el usuario.
     */
    public void CreaUsuario() {
        String usuario = SolicitaUsuario(false);
        if (usuario == null) return;

        String contrasena = SolicitaContrasena(false);
        if (contrasena == null) return;

        controller.registraUsuario(new Usuario(usuario, contrasena));
        logger.log(Level.INFO, "Usuarios registrados en el sistema: {0}", controller.obtieneListaUsuarios());
    }
}
