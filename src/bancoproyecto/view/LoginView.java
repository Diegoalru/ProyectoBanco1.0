package bancoproyecto.view;

import bancoproyecto.controller.MainController;
import bancoproyecto.controller.UserController;
import bancoproyecto.models.UserLogin;

import javax.swing.*;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginView extends JFrame {
    private final UserController userController = new UserController();
    private final ResourceBundle bundle = ResourceBundle.getBundle("bancoproyecto.resources.Strings");
    private JPanel MainPanel;
    private JPanel Pnl_Error;
    private JTextField Txt_Username;
    private JPasswordField Txt_Password;
    private JButton Btn_Ok;
    private JButton Btn_Cancel;
    private JLabel Lbl_Errors;

    public LoginView() {
        initComponents();

        Btn_Cancel.addActionListener(event -> dispose());
        Btn_Ok.addActionListener(event -> Login());

        ShowErrorMessages(null); // Ocultar los mensajes de error
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

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                String error = null;
                var userLogin = new UserLogin(username, String.valueOf(password));

                // Limpiar el campo de contraseña
                Arrays.fill(password, ' ');

                try {
                    userController.LoginUser(userLogin);
                } catch (Exception e) {
                    error = e.getMessage();
                }

                return error;
            }

            @Override
            protected void done() {
                try {
                    String error = get(); // Obtiene el resultado de doInBackground()
                    if (error == null || error.isEmpty()) {
                        MainController.OpenMainView();
                        Stop();
                    } else {
                        ShowErrorMessages(error);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    private void ShowErrorMessages(String error) {
        if (error == null || error.isEmpty()) {
            Pnl_Error.setVisible(false);
            return;
        }

        Lbl_Errors.setText(error);
        Pnl_Error.setVisible(true);
    }

    public void Start() {
        setVisible(true);
    }

    public void Stop() {
        dispose();
    }
}
