package bancoproyecto.view;

import bancoproyecto.controller.UsuarioController;
import bancoproyecto.models.Usuario;

import javax.swing.*;

public class RegisterView extends JFrame {
    private final UsuarioController usuarioController = new UsuarioController();
    private JPanel MainPanel;
    private JTextField Txt_Name;
    private JTextField Txt_Username;
    private JPasswordField Txt_Password;
    private JButton Btn_Ok;
    private JButton Btn_Cancel;
    private JPanel Pnl_Error;
    private JLabel Lbl_Errors;

    public RegisterView() {
        initComponents();

        Btn_Cancel.addActionListener(event -> dispose());
        Btn_Ok.addActionListener(event -> Register());

        ShowErrorMessages(null); // Ocultar los mensajes de error
    }


    private void initComponents() {
        setTitle("Register");
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

    private void Register() {
        var name = Txt_Name.getText();
        var username = Txt_Username.getText();
        var password = new String(Txt_Password.getPassword());

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            ShowErrorMessages("All fields are required");
            return;
        }

        new Thread(() -> {
            String error = null;
            Usuario usuario = new Usuario(name, username, password);
            try {
                usuarioController.RegistraUsuario(usuario);
            } catch (Exception e) {
                error = e.getMessage();
            } finally {
                if (error == null) {
                    JOptionPane.showMessageDialog(
                            this,
                            "User created successfully",
                            "Register",
                            JOptionPane.INFORMATION_MESSAGE);
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
