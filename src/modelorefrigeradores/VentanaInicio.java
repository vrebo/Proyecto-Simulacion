package modelorefrigeradores;

import componentes.BarraMenu;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vistas.VistaPrincipal;

/**
 *
 * @author VREBO
 */
public class VentanaInicio extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
        }
        new VentanaInicio(new BarraMenu());
    }
    public VentanaInicio(BarraMenu barra){
        setTitle("Modelo de Simulación Ejercicio 4 capitulo 3.");
        VistaPrincipal vista = new VistaPrincipal();
        setJMenuBar(barra);
        setLocation(100,100);
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(vista);
        String url = "/iconos/icono.png";
        setIconImage(new ImageIcon(getClass().getResource(url)).getImage());
        setVisible(true);
    }
    
}