package bancoproyecto.controller;

import bancoproyecto.models.Usuario;
import bancoproyecto.view.CredencialesView;
import bancoproyecto.view.InicioView;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class.getName());
    private static final InicioView inicioView = new InicioView();
    private static CredencialesView credencialesView;
    private static Usuario usuario = null;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        MainController.usuario = usuario;
    }

    public static void OpenGUI() {
        inicioView.Start();
    }

    public static void OpenRegisterView() {
        credencialesView = new CredencialesView(CredencialesView.TipoCredencial.Registro);
        credencialesView.Start();
    }

    public static void OpenLoginView() {
        credencialesView = new CredencialesView(CredencialesView.TipoCredencial.InicioSesion);
        credencialesView.Start();
    }

    public static void OpenMainView() {
        try {
            if (usuario == null) {
                throw new Exception("No se ha iniciado sesión");
            }

            // Cerrar la ventana de inicio
            inicioView.Stop();

            // Abrir la ventana principal
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getUsuario());
            logger.log(Level.INFO, "Bienvenido " + usuario.getUsuario());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            logger.log(Level.SEVERE, "Error al iniciar sesion " + e.getMessage());
        }
    }
}
