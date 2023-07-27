package bancoproyecto.view;

import bancoproyecto.controller.UserController;
import bancoproyecto.models.UserRegister;

import javax.swing.*;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CancellationException;
import java.util.logging.Logger;

public class RegisterView extends JFrame {
    private final UserController userController = new UserController();
    private final ResourceBundle bundle = ResourceBundle.getBundle("bancoproyecto.resources.Strings");
    private static final Logger logger = Logger.getLogger(RegisterView.class.getName());
    private JPanel MainPanel;
    private JTextField Txt_Name;
    private JTextField Txt_Username;
    private JPasswordField Txt_Password;
    private JButton Btn_Ok;
    private JButton Btn_Cancel;
    private JPanel Pnl_Error;
    private JLabel Lbl_Errors;
    private JProgressBar Pgb_Loading;
    private SwingWorker<String, Void> worker;

    public RegisterView() {
        initComponents();

        Btn_Cancel.addActionListener(e -> Stop());
        Btn_Ok.addActionListener(e -> Register());

        ShowErrorMessages(null); // Ocultar los mensajes de error
        Pgb_Loading.setVisible(false);
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

        worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                String error = null;
                var userRegister = new UserRegister(name, username, String.valueOf(password));
                Arrays.fill(password, ' ');

                try {
                    // TODO: Enviar chunks de progreso.
                    publish();
                    userController.RegisterUser(userRegister);
                } catch (Exception e) {
                    error = e.getMessage();
                }
                return error;
            }

            @Override
            protected void done() {
                try {
                    Pgb_Loading.setVisible(false);
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
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }
        dispose();
    }

}
