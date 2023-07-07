/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoproyecto;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author DuchetCR
 */
public class Usuario {
    private String usuario;
    private String contraseña;

    public Usuario() {
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return "*".repeat(this.contraseña.length());
    }

    public void setContraseña() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Cree su Contraseña:"));
        panel.add(new JLabel("La contraseña debe tener al menos 8 caracteres"));
        panel.add(new JLabel("No se permiten caracteres iguales consecutivos"));
        panel.add(passwordField);
        JOptionPane.showMessageDialog((Component)null, panel);
        this.contraseña = new String(passwordField.getPassword());
        if (this.contraseña.length() < 8) {
            JOptionPane.showMessageDialog((Component)null, "La contraseña debe tener al menos 8 caracteres");
        } else {
            for(int i = 0; i < this.contraseña.length() - 1; ++i) {
                if (this.contraseña.charAt(i) == this.contraseña.charAt(i + 1)) {
                    JOptionPane.showMessageDialog((Component)null, "No se permiten caracteres iguales consecutivos");
                    return;
                }
            }

            this.contraseña = this.contraseña;
        }
    }
}
