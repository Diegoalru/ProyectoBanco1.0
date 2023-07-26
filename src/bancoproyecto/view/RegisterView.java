package bancoproyecto.view;

import bancoproyecto.controller.UserController;
import bancoproyecto.models.UserRegister;

import javax.swing.*;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RegisterView extends JFrame {
    private final UserController userController = new UserController();
    private final ResourceBundle bundle = ResourceBundle.getBundle("bancoproyecto.resources.Strings");
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
        setTitle(bundle.getString("register_title"));
        setContentPane(MainPanel);
        setResizable(true);
        setSize(400, 335);
        setPreferredSize(new java.awt.Dimension(400, 335));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    private void Register() {
        var name = Txt_Name.getText();
        var username = Txt_Username.getText();
        var password = Txt_Password.getPassword();

        if (name.isEmpty() || username.isEmpty() || password.length == 0) {
            ShowErrorMessages(bundle.getString("register_error_all_fields_required"));
            return;
        }

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                String error = null;
                var userRegister = new UserRegister(name, username, String.valueOf(password));
                Arrays.fill(password, ' ');

                try {
                    userController.RegisterUser(userRegister);
                } catch (Exception e) {
                    error = e.getMessage();
                }

                return error;
            }

            @Override
            protected void done() {
                try {
                    String error = get();
                    if (error == null) {
                        JOptionPane.showMessageDialog(
                                RegisterView.this,
                                bundle.getString("register_success_message"),
                                bundle.getString("register_success_title"),
                                JOptionPane.INFORMATION_MESSAGE);
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
