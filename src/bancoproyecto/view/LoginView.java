package bancoproyecto.view;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame {
    private JPanel MainPanel;
    private JPanel Pnl_Error;
    private JTextField Txt_Username;
    private JTextField Txt_Password;
    private JButton Btn_Ok;
    private JButton Btn_Cancel;
    private JLabel Lbl_Errors;

    public LoginView() {
        initComponents();
        ShowErrorMessages(null);

        Btn_Cancel.addActionListener(event -> dispose());

    }

    private void initComponents() {
        setTitle("Inicio de sesión");
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 335));
        setResizable(true);
        pack();
    }

    public void Start(){
        setVisible(true);
    }

    private void Login() {
    }

    private void ShowErrorMessages(String[] errors){
        if (errors == null || errors.length == 0) {
            Pnl_Error.setVisible(false);
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String error : errors) {
            message.append(error).append("\n");
        }

        Lbl_Errors.setText(message.toString());
        Pnl_Error.setVisible(true);
    }
}
