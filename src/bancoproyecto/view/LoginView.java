package bancoproyecto.view;

import bancoproyecto.controller.MainController;
import bancoproyecto.controller.UserController;
import bancoproyecto.models.UserLogin;

import javax.swing.*;
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

    public void Start() {
        setVisible(true);
    }

    public void Stop() {
        dispose();
    }

    private void Login() {
        var username = Txt_Username.getText();
        var password = new String(Txt_Password.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            ShowErrorMessages(bundle.getString("login_empty_fields"));
            return;
        }

        new Thread(() -> {
            String error = null;
            UserLogin userlogin = new UserLogin(username, password);

            try {
                userController.LoginUser(userlogin);
            } catch (Exception e) {
                error = e.getMessage();
            } finally {
                if (error == null || error.isEmpty()) {
                    MainController.OpenMainView();
                    Stop();
                } else {
                    ShowErrorMessages(error);
                }
            }
        }).start();
    }

    private void ShowErrorMessages(String error) {
        if (error == null || error.isEmpty()) {
            Pnl_Error.setVisible(false);
            return;
        }

        Lbl_Errors.setText(error);
        Pnl_Error.setVisible(true);
    }
}
