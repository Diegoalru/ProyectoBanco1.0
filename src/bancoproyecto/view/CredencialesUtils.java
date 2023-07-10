package bancoproyecto.view;

import javax.swing.*;
import java.awt.*;

public class CredencialesUtils {

    /**
     * Solicita el usuario al usuario.
     *
     * @param esInicioSesion Indica si el usuario se solicita para iniciar sesion o
     *                       para crear un usuario.
     *
     * @return El nombre de usuario ingresado por el usuario.
     */
    public static String SolicitaUsuario(Boolean esInicioSesion) {
        String usuario = null; // Inicializar la variable para que no marque error.
        boolean validInput = false; // Bandera para validar la entrada del usuario.

        while (!validInput) {
            try {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JTextField userField = new JTextField();
                panel.add(new JLabel("Usuario:"));

                if (!esInicioSesion) {
                    panel.add(new JLabel("El usuario debe tener al menos 3 caracteres"));
                }

                panel.add(userField);

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        (esInicioSesion ? "Inicio de sesion" : "Crear usuario"), // Título de la ventana
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (opcion == JOptionPane.OK_OPTION) {
                    usuario = userField.getText();
                } else {
                    validInput = true;
                    return null;
                }
//                else
//                {
//                    var salir = JOptionPane.showConfirmDialog(null, "¿Desear salir?", "Salir",
//                            JOptionPane.YES_NO_OPTION);
//
//                    if (salir == JOptionPane.YES_OPTION) {
//                        System.exit(0);
//                    }
//
//                    throw new Exception("Ingrese de nuevo el usuario");
//                }

                if (usuario.isEmpty()) {
                    throw new Exception("El usuario no puede estar vació");
                }

                int longitudMininaUsuario = 3;

                if (usuario.length() < longitudMininaUsuario) {
                    throw new Exception("El usuario debe tener al menos " + longitudMininaUsuario + " caracteres");
                }

                validInput = true;
            } catch (NullPointerException e) {
                // En caso de que el usuario presione el botón de cancelar, cierre la ventana o
                // presione la tecla ESC.
                JOptionPane.showMessageDialog(null, "El usuario no puede estar vació");
            } catch (Exception e) {
                // En caso de que el usuario no cumpla con las validaciones, se muestra el
                // mensaje de error.
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return usuario;
    }

    /**
     * Solicita la contrasena al usuario.
     *
     * @param esInicioSesion Indica si la contraseña se solicita para iniciar sesión
     *                       o para crear un usuario.
     * @return La contraseña ingresada por el usuario.
     */
    public static String SolicitaContrasena(Boolean esInicioSesion) {
        String contrasena = null; // Inicializar la variable para que no marque error.
        boolean validInput = false; // Bandera para validar la entrada del usuario.

        while (!validInput) {
            try {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JPasswordField passwordField = new JPasswordField();
                panel.add(new JLabel("Contraseña:"));

                if (!esInicioSesion) {
                    panel.add(new JLabel("La contraseña debe tener al menos 8 caracteres"));
                    panel.add(new JLabel("No se permiten caracteres iguales consecutivos"));
                }

                panel.add(passwordField);

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        (esInicioSesion ? "Inicio de sesion" : "Crear contraseña"), // Título de la ventana
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (opcion == JOptionPane.OK_OPTION) {
                    contrasena = new String(passwordField.getPassword());
                } else {
                    validInput = true;
                    return null;
                }
//                else
//                {
//                    var salir = JOptionPane.showConfirmDialog(null, "¿Desear salir?", "Salir",
//                            JOptionPane.YES_NO_OPTION);
//
//                    if (salir == JOptionPane.YES_OPTION) {
//                        System.exit(0);
//                    }
//
//                    throw new Exception("Ingrese de nuevo la contrasena");
//                }

                // #region Validaciones de contraseña
                if (contrasena.isEmpty()) {
                    throw new Exception("La contrasena no puede estar vaciá");
                }

                int longitudMinimaContrasena = 8;

                if (contrasena.length() < longitudMinimaContrasena) {
                    throw new Exception(
                            "La contrasena debe tener al menos " + longitudMinimaContrasena + " caracteres");
                }

                if (contrasena.matches(".*([a-zA-Z])\\1+.*")) {
                    throw new Exception("La contrasena no puede tener caracteres iguales consecutivos");
                }
                // #endregion

                validInput = true;
            } catch (NullPointerException e) {
                // En caso de que el usuario presione el botón de cancelar, cierre la ventana o
                // presione la tecla ESC.
                JOptionPane.showMessageDialog(null, "La contrasena no puede estar vaciá");
            } catch (Exception e) {
                // En caso de que el usuario no cumpla con las validaciones, se muestra el
                // mensaje de error.
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        return contrasena;
    }
}
