package bancoproyecto.view;

import bancoproyecto.controller.UsuarioController;
import bancoproyecto.models.Usuario;

import javax.swing.JOptionPane;

import static bancoproyecto.view.CredencialesUtils.SolicitaContrasena;
import static bancoproyecto.view.CredencialesUtils.SolicitaUsuario;

public class InicioSesionView {

    private final UsuarioController controller = new UsuarioController();

    /**
     * Inicia sesion con los datos ingresados por el usuario.
     */
    public void IniciarSesionUsuario() {
        String usuario = SolicitaUsuario(true);
        if (usuario == null) return;

        String contrasena = SolicitaContrasena(true);
        if (contrasena == null) return;

        // Crear un objeto de tipo Usuario con los datos ingresados por el usuario.
        Usuario usuarioInicioSesion = new Usuario(usuario, contrasena);

        // Validar las credenciales del usuario.
        Boolean inicioSesionResultado = controller.InicioSesion(usuarioInicioSesion);

        if (inicioSesionResultado) {
            JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso", "Inicio de sesion",
                    JOptionPane.INFORMATION_MESSAGE);

            // TODO: Agregar el código para mostrar el menu principal.

        } else {
            JOptionPane.showMessageDialog(null, "Inicio de sesion fallido", "Inicio de sesion",
                    JOptionPane.ERROR_MESSAGE);
            IniciarSesionUsuario(); // Volver a solicitar usuario y contraseña.
        }
    }
}
