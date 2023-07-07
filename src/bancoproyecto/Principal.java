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
            JPasswordField Censura = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, Censura, "Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            char[] ContaseñaChars = Censura.getPassword();
            String Contraseña = new String(ContaseñaChars);

            if (usuario.equals(u1.getUsuario()) && Contraseña.equals(u1.getContraseña())) {
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
