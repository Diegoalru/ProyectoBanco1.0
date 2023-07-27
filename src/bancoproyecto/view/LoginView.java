package bancoproyecto.view;

import bancoproyecto.controller.MainController;
import bancoproyecto.controller.UserController;
import bancoproyecto.models.UserLogin;

import javax.swing.*;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CancellationException;
import java.util.logging.Logger;


public class LoginView extends JFrame {
    private final UserController userController = new UserController();
    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    private final ResourceBundle bundle = ResourceBundle.getBundle("bancoproyecto.resources.Strings");
    private JPanel MainPanel;
    private JPanel Pnl_Error;
    private JTextField Txt_Username;
    private JPasswordField Txt_Password;
    private JButton Btn_Ok;
    private JButton Btn_Cancel;
    private JLabel Lbl_Errors;
    private JProgressBar Pgb_Loading;
    private SwingWorker<String, Void> worker;

    public LoginView() {
        initComponents();

        Btn_Cancel.addActionListener(event -> Stop());
        Btn_Ok.addActionListener(event -> Login());

        ShowErrorMessages(null); // Ocultar los mensajes de error
        Pgb_Loading.setVisible(false);
    }

    private void initComponents() {
        setTitle(bundle.getString("login_title"));
        setContentPane(MainPanel);
        setResizable(true);
        setSize(400, 335);
        setPreferredSize(new java.awt.Dimension(400, 335));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    private void Login() {
        var username = Txt_Username.getText();
        var password = Txt_Password.getPassword();

        if (username.isEmpty() || password.length == 0) {
            ShowErrorMessages(bundle.getString("login_empty_fields"));
            return;
        }

        // Limpiar el campo de contraseña
        // Obtiene el resultado de doInBackground()
        worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                String error = null;
                var userLogin = new UserLogin(username, String.valueOf(password));
                Arrays.fill(password, ' '); // Limpiar el campo de contraseña

                try {
                    // TODO: Enviar chunks de progreso.
                    publish(); // Ejecuta el método process()
                    userController.LoginUser(userLogin);
                } catch (Exception e) {
                    error = e.getMessage(); // Muestra los errores.
                }
                return error;
            }

            @Override
            protected void done() {
                try {
                    Pgb_Loading.setVisible(false); // Ocultar la barra de progreso
                    String error = get(); // Obtiene el resultado de doInBackground()

                    if (error == null || error.isEmpty()) {
                        MainController.OpenMainView();
                        Stop();
                    } else {
                        ShowErrorMessages(error);
                    }
                } catch (CancellationException e) {
                    logger.info("Se ha cancelado el inicio de sesión.");
                } catch (Exception e) {
                    logger.severe(e.getMessage());
                }
            }

            @Override
            protected void process(java.util.List<Void> chunks) {
                Pgb_Loading.setVisible(true);
            }
        };

        worker.execute();
    }

    private void ShowErrorMessages(String error) {
        if (error == null || error.isEmpty()) {
            Pnl_Error.setVisible(false);
            return;
        }

        Pgb_Loading.setVisible(false);
        Lbl_Errors.setText(error);
        Pnl_Error.setVisible(true);
    }

    public void Start() {
        setVisible(true);
    }

    public void Stop() {
        // Si worker esta corriendo, cancelarlo.
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }

        dispose();
    }
}
