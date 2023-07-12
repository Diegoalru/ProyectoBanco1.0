package bancoproyecto.controller;

import bancoproyecto.models.Usuario;
import bancoproyecto.view.CredencialesView;
import bancoproyecto.view.InicioView;
import bancoproyecto.view.LoginView;
import bancoproyecto.view.RegisterView;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class.getName());
    private static final InicioView inicioView = new InicioView();
    private static RegisterView registerView;
    private static LoginView loginView;
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
        registerView = new RegisterView();

        if (loginView != null && loginView.isVisible()) {
            loginView.Stop();
        }

        registerView.Start();
    }

    public static void OpenLoginView() {
        loginView = new LoginView();

        if (registerView != null && registerView.isVisible()) {
            registerView.Stop();
        }

        loginView.Start();
    }

    public static void OpenMainView() {
        try {
            if (usuario == null) {
                throw new Exception("No se ha iniciado sesión");
            }

            // Cerrar la ventana de inicio
            inicioView.Stop();

            // Abrir la ventana principal
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.name());
            logger.log(Level.INFO, "Bienvenido " + usuario.name());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            logger.log(Level.SEVERE, "Error al iniciar sesion " + e.getMessage());
        }
    }
}
