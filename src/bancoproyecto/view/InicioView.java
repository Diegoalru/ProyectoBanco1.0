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
        setResizable(true);
        setSize(300, 150);
        setPreferredSize(new java.awt.Dimension(300, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    public void Start() {
        this.setVisible(true);
    }

    public void Stop() {
        this.dispose();
    }
}
