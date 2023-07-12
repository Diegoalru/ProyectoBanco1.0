package bancoproyecto.controller;

import bancoproyecto.models.User;
import bancoproyecto.view.StartView;
import bancoproyecto.view.LoginView;
import bancoproyecto.view.RegisterView;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class.getName());
    private static final StartView START_VIEW = new StartView();
    private static RegisterView registerView;
    private static LoginView loginView;
    private static User user = null;

    public static User getUsuario() {
        return user;
    }

    public static void setUsuario(User user) {
        MainController.user = user;
    }

    public static void OpenGUI() {
        START_VIEW.Start();
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
            if (user == null) {
                throw new Exception("No se ha iniciado sesión");
            }

            // Cerrar la ventana de inicio
            START_VIEW.Stop();

            // Abrir la ventana principal
            JOptionPane.showMessageDialog(null, "¡Bienvenido %s!".formatted(user.getUser()));
            logger.log(Level.INFO, "User %s logged in".formatted(user.getUser()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            logger.log(Level.SEVERE, "Error al iniciar sesion " + e.getMessage());
        }
    }
}
