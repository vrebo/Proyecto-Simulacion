package vistas;

import controladores.ControladorPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author VREBO
 */
public class Panel extends JPanel {

    private JPanel panelCentro;
    private JComboBox<String> corridas;

    public Panel() {
        addComponentes();
        addEventos();
    }

    private void addComponentes() {
        corridas = new JComboBox<>();
        panelCentro = new JPanel(new CardLayout());
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Corrida: "));
        panel.add(corridas);
        add(panel, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
    }

    public void addCorrida(VistaCorrida vistaCorrida, String numCorrida) {
        panelCentro.add(numCorrida, vistaCorrida);
    }

    public JComboBox<String> getCorridas() {
        return corridas;
    }

    private void addEventos() {
        ControladorPanel controlador = new ControladorPanel(this);
        corridas.addItemListener(controlador);
    }
}
