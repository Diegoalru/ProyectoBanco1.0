/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bancoproyecto;


import javax.swing.JOptionPane;


/**
 *
 * @author DuchetCR
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario u1 = new Usuario();
        u1.setUsuario(JOptionPane.showInputDialog("Cree su Usuario: "));
        u1.setContraseña();
        System.out.println(u1.getUsuario());
        System.out.println(u1.getContraseña());
        for (int i = 0; i < 3; i++) {
            String usuario = JOptionPane.showInputDialog("Usuario: ");
            String password = JOptionPane.showInputDialog("Password: ");
        if (usuario.equals(u1.getUsuario()) && password.equals(u1.getContraseña())) {
            JOptionPane.showMessageDialog(null,"Entro al sistema");
            // Resto del código aquí
   
            
            
            
            
            break;
        } else if (i < 2) {
            JOptionPane.showMessageDialog(null,"Usuario rechazado, intenta nuevamente");
        } else if (i == 2) {
            JOptionPane.showMessageDialog(null,"Usuario bloqueado, vuelve a intentar más tarde");
}
        }
    }
}  
