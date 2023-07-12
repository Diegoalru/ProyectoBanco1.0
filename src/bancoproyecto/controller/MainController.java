package bancoproyecto.controller;

import bancoproyecto.models.User;
import bancoproyecto.view.LoginView;
import bancoproyecto.view.RegisterView;
import bancoproyecto.view.StartView;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class.getName());
    private static final ResourceBundle bundle = ResourceBundle.getBundle("bancoproyecto.resources.Strings");
    private static final StartView startView = new StartView();
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
        startView.Start();
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
                throw new Exception(bundle.getString("login_failed"));
            }

            // Cerrar la ventana de inicio
            startView.Stop();

            // Abrir la ventana principal
            JOptionPane.showMessageDialog(null,
                    MessageFormat.format(bundle.getString("welcome_user"), user.getUser()),
                    bundle.getString("welcome_title"),
                    JOptionPane.INFORMATION_MESSAGE
            );
            logger.log(Level.INFO, "User has logged in");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    bundle.getString("login_failed"),
                    bundle.getString("login_failed"),
                    JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, bundle.getString("login_failed"), e);
        }
    }
}
