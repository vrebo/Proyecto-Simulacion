package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author VREBO
 */
public class Panel extends JPanel {
    
    private JLabel jlblpromedioCorridas;
    private JPanel panelCentro;
    private JComboBox<String> corridas;

    public Panel() {
        addComponentes();
        addEventos();
    }

    private void addComponentes() {
        corridas = new JComboBox<>();
        jlblpromedioCorridas = new JLabel();
        jlblpromedioCorridas.setHorizontalAlignment(JLabel.RIGHT);
        jlblpromedioCorridas.setFont(new Font("Arial", Font.BOLD, 15));
        panelCentro = new JPanel(new CardLayout());
        
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Corrida: "));
        panel.add(corridas);
        add(panel, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(jlblpromedioCorridas, BorderLayout.SOUTH);
    }

    public void addCorrida(VistaCorrida vistaCorrida, String numCorrida) {
        panelCentro.add(numCorrida, vistaCorrida);
    }

    public JComboBox<String> getCorridas() {
        return corridas;
    }

    private void addEventos() {
        corridas.addItemListener((ItemEvent e) -> {
            String origen = (String) e.getItem();
            CardLayout card = (CardLayout) panelCentro.getLayout();
            card.show(panelCentro, origen);
        });
    }
    
    public void setjLblPromedioCorridas(String promedio){
        jlblpromedioCorridas.setText(promedio);
    }
}
