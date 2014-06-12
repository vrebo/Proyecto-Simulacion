package componentes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author VREBO
 */
public class BarraMenu extends JMenuBar {

    private JMenuItem jmiAcercaDe;
    private final String ETQ_JM_ACERCA_DE = "Acerca de";

    public BarraMenu() {
        addComponentes();
        addEventos();
    }

    private void addComponentes() {
        JMenu informacion = new JMenu("Información");
        String url = "/iconos/informacionx24.png";
        ImageIcon icono = new ImageIcon(getClass().getResource(url));
        jmiAcercaDe = new JMenuItem(ETQ_JM_ACERCA_DE, icono);
        informacion.add(jmiAcercaDe);
        add(informacion);
    }

    private void addEventos() {
        jmiAcercaDe.addActionListener((ActionEvent e) -> {
            String url = "/iconos/icono.png",
                    texto = "Desarrollado por:\n\n"
                    + "Domínguez Valladares Josué\n"
                    + "Olivo Betanzos Rebeca\n"
                    + "Rojas Aguirre Dolores\n"
                    + "Rebolloso Degante Víctor Daniel\n\n"
                    + "Para la asignatura de Simulación de la carrera de Ingeniería en Sistemas Computacionales\n"
                    + "en el Instituto Tecnológico de Veracruz al 12 de Junio del 2014";
            ImageIcon icono = new ImageIcon(getClass().getResource(url));
            JOptionPane.showMessageDialog(this, texto, ETQ_JM_ACERCA_DE, JOptionPane.INFORMATION_MESSAGE, icono);
        });
    }
}
