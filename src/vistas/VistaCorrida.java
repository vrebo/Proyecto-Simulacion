/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import componentes.Tabla;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modelos.Corrida;

/**
 *
 * @author VREBO
 */
public class VistaCorrida extends JPanel {

    public static String POLITICAS = "60 70 80 90 100";
    public static String CORRIDAS = "5";
    
    private final String[] titulos = {
        "DÍA", "PRODUCCIÓN",
        "DEMANDA DIARIA", "FALTANTES",
        "COSTO X FATANTE", "SOBRANTE",
        "COSTO X INVENTARIO", "COSTO TOTAL"
    };
    private final Corrida corrida;    
    
    public VistaCorrida(Corrida corrida) {
        this.corrida = corrida;
        addComponentes();
    }

    private void addComponentes() {
        setLayout(new BorderLayout());
        Tabla tabla = new Tabla(corrida.getDatos(), titulos);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        JLabel etiqueta = new JLabel("Costo Promedio de la corrida: $" + corrida.getCostoPromedioCorrida(), JLabel.RIGHT);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 15));
        add(etiqueta, BorderLayout.SOUTH);
    }

}
