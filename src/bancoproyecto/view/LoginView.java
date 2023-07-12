package bancoproyecto.view;

import bancoproyecto.controller.MainController;
import bancoproyecto.controller.UsuarioController;
import bancoproyecto.models.Usuario;

import javax.swing.*;

public class LoginView extends JFrame {
    private final UsuarioController usuarioController = new UsuarioController();
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
        setTitle("Login");
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
            ShowErrorMessages("All fields are required");
            return;
        }

        new Thread(() -> {
            String error = null;
            Usuario usuario = new Usuario(null, username, password);
            Usuario datosUsuario = null;

            try {
                datosUsuario = usuarioController.InicioSesion(usuario);
            } catch (Exception e) {
                error = e.getMessage();
            } finally {
                if (error == null || error.isEmpty()) {
                    MainController.OpenMainView(datosUsuario);
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
