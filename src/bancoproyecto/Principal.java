package bancoproyecto;

import bancoproyecto.controller.MainController;

import javax.swing.*;
import java.util.logging.Logger;

public class Principal {

    /**
     * Logger para la clase Principal.
     */
    private static final Logger logger = Logger.getLogger(Principal.class.getName());

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Establece el look and feel de la aplicación.
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                logger.warning("No se pudo establecer el look and feel");
            }

            logger.info("Iniciando programa...");
            MainController.OpenGUI();
        });
    }
}
