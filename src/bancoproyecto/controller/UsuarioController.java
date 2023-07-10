package bancoproyecto.controller;

import bancoproyecto.data.UsuarioData;
import bancoproyecto.models.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class UsuarioController {
    public void registraUsuario(Usuario usuario) {
        UsuarioData.NuevoUsuario(usuario);
    }

    public Boolean InicioSesion(Usuario usuarioInicioSesion) {
        return UsuarioData.InicioSesion(usuarioInicioSesion);
    }

    public String obtieneListaUsuarios() {
        return UsuarioData.ObtieneListaUsuarios().toString();
    }
}
