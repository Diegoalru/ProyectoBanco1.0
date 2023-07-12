package bancoproyecto.view;

import bancoproyecto.controller.MainController;
import bancoproyecto.controller.UsuarioController;
import bancoproyecto.models.Usuario;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CredencialesView extends JFrame {

    private final UsuarioController usuarioController = new UsuarioController();
    private JPanel MainPanel;
    private JButton Btn_Ok;
    private JButton Btn_Cancelar;
    private JTextField Txt_Usuario;
    private JPasswordField Txt_Contrasena;
    private JLabel Lbl_Titulo_Errores;
    private JLabel Lbl_Errores;

    public CredencialesView(TipoCredencial tipoCredencial) {
        initComponents(tipoCredencial);

        Btn_Cancelar.addActionListener(event -> dispose());

        Btn_Ok.addActionListener(event -> {
            if (tipoCredencial == TipoCredencial.Registro) {
                CrearUsuario();
            } else {
                IniciarSesion();
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();  // Cerrar la ventana al presionar ESC
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (tipoCredencial == TipoCredencial.Registro) {
                        CrearUsuario();
                    } else {
                        IniciarSesion();
                    }
                }
            }
        });

        setFocusable(true);
        requestFocus();
    }

    private void initComponents(TipoCredencial tipoCredencial) {
        setTitle(tipoCredencial == TipoCredencial.Registro ? "Registro" : "Inicio de sesion");
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 275));
        setResizable(true);
        pack();
    }

    public void Start() {
        this.setVisible(true);
    }

    public void CrearUsuario() {
        if (Txt_Usuario.getText().isEmpty() || Txt_Contrasena.getPassword().length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, ingrese todos los datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            //usuarioController.RegistraUsuario(new Usuario(Txt_Usuario.getText(), new String(Txt_Contrasena.getPassword())));
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado con éxito",
                    "Registro",
                    JOptionPane.INFORMATION_MESSAGE);
            usuarioController.ObtieneListaUsuarios();
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void IniciarSesion() {
        if (Txt_Usuario.getText().isEmpty() || Txt_Contrasena.getPassword().length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, ingrese todos los datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Usuario datosUsuario = usuarioController.InicioSesion(new Usuario(null, Txt_Usuario.getText(), new String(Txt_Contrasena.getPassword())));

            if (datosUsuario == null) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Inicio de sesión", JOptionPane.ERROR_MESSAGE);
                Txt_Usuario.setText("");
                Txt_Contrasena.setText("");
                Txt_Usuario.requestFocus();
                return;
            }

            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
            MainController.setUsuario(datosUsuario);

            this.dispose();

            MainController.OpenMainView();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public enum TipoCredencial {
        Registro, InicioSesion
    }
}
