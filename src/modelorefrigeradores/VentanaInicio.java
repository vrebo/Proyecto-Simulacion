/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelorefrigeradores;

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
        VentanaInicio m = new VentanaInicio();
    }
    
    public VentanaInicio(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
        }
        setTitle("Modelo de Simulación Problema 4 capitulo 3.");
        VistaPrincipal vista = new VistaPrincipal();
        setLocation(100,100);
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(vista);
        String url = "/iconos/icono.png";
        setIconImage(new ImageIcon(getClass().getResource(url)).getImage());
        setVisible(true);
    }
    
}