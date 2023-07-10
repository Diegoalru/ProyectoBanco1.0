package bancoproyecto.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InicioView extends JFrame {
    private JButton Btn_CrearUsuario;
    private JButton Btn_IniciarSesion;
    private JPanel MyPane;

    public InicioView() {
        initComponents();

        Btn_CrearUsuario.addActionListener(actionEvent -> {
            CreaCuentaView creaCuentaView = new CreaCuentaView();
            creaCuentaView.CreaUsuario();
        });

        Btn_IniciarSesion.addActionListener(actionEvent -> {
            InicioSesionView inicioSesionView = new InicioSesionView();
            inicioSesionView.IniciarSesionUsuario();
        });
    }

    /**
     * Inicializa los componentes de la ventana y ajusta sus propiedades.
     */
    private void initComponents() {
        setTitle("Inicio");
        setContentPane(MyPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 150));
        setResizable(true);
        pack();
    }
}
