package vistas;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author VREBO
 */
public class VistaResultados extends JFrame {

    private JTabbedPane pestanas;

    public VistaResultados() {
        addComponentes();
        setSize(800, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addComponentes() {
        pestanas = new JTabbedPane();
        add(pestanas);
    }

    public JTabbedPane getPestanas() {
        return pestanas;
    }

    public void addPestana(String titulo) {
        pestanas.addTab(titulo, new Panel());
    }

    public Component getPestana(int index) {
        System.out.println(pestanas.getTabComponentAt(index));
        return pestanas.getComponentAt(index);
    }
}
