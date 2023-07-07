/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bancoproyecto;


import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author DuchetCR
 */
public class Principal {

    public static void main(String[] args) {
        Usuario u1 = new Usuario();
        u1.setUsuario(JOptionPane.showInputDialog("Cree su Usuario: "));
        u1.setContraseña();
        System.out.println(u1.getUsuario());
        System.out.println(u1.getContraseña());
        
        for (int i = 0; i < 3; i++) {
            String usuario = JOptionPane.showInputDialog("Usuario: ");
            JPasswordField passwordField = new JPasswordField();
            passwordField.setEchoChar('*');
            int option = JOptionPane.showConfirmDialog(null, passwordField, "Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION && usuario.equals(u1.getUsuario()) && passwordField.getPassword().length > 0) {
                JOptionPane.showMessageDialog(null, "Entro al sistema");
                // Resto del código aquí
                
                
                
                
                
                
                break;
            } else if (i < 2) {
                JOptionPane.showMessageDialog(null, "Usuario rechazado, intenta nuevamente");
            } else if (i == 2) {
                JOptionPane.showMessageDialog(null, "Usuario bloqueado, vuelve a intentar más tarde");
            }
        }
    }
}
