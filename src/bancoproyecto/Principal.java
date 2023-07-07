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
    
    }  
}
