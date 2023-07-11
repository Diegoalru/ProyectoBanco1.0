package bancoproyecto.view;

import bancoproyecto.controller.MainController;

import javax.swing.*;

public class InicioView extends JFrame {
    private JButton Btn_CrearUsuario;
    private JButton Btn_IniciarSesion;
    private JPanel MainPanel;

    public InicioView() {
        initComponents();

        Btn_CrearUsuario.addActionListener(actionEvent -> {
            MainController.OpenRegisterView();
        });

        Btn_IniciarSesion.addActionListener(actionEvent -> {
            MainController.OpenLoginView();
        });
    }

    /**
     * Inicializa los componentes de la ventana y ajusta sus propiedades.
     */
    private void initComponents() {
        setTitle("Inicio");
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 150));
        setResizable(true);
        pack();
    }

    public void Start() {
        this.setVisible(true);
    }

    public void Stop() {
        this.setVisible(false);
        this.dispose();
    }
}
