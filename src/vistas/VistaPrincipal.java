package vistas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author VREBO
 */
public class VistaPrincipal extends JPanel{
    private String texto = 
              "Se tiene un proceso de fabricación de refrigeradores. La demanda diaria\n"
            + "de este producto está normalmente distribuida. La demanda promedio es de\n"
            + "80 refrigeradores por día, con una desviación estándar de 10 refrigeradores\n"
            + "diarios. Se desea saber cuál es la mejor política de producción, considerando\n"
            + "60, 70, 80, 90 y 100 refrigeradores por día. el costo por faltante es de \n"
            + "$8/refrigerador por día, y el costo de tener un refrigerador en el inventario\n"
            + "es de $5/refrigerador por día.\n\n"
            + "a) Se le pide realizar 5 corridas de 100 días para cada política.\n"
            + "b) Obtenga el costo promedio por día de cada política, y un intervalo\n"
            + "de confianza para ese promedio diario.\n"
            + "c) Determine, con base en sus resultados, cuál de las políticas\n"
            + "seleccionadas es la que debe implementar la empresa.";
    private final String ETQ_JCBX_SELECCION = "Especificaciones propias";
    private final String ETQ_POLITICAS = "Políticas de producción: ";
    private final String ETQ_COSTO_FALTANTE = "Costo por faltante: ";
    private final String ETQ_COSTO_INVENTARIO = "Costo de inventario: ";
    private final String ETQ_CORRIDAS = "Número de corridas: ";
    private final String ETQ_DIAS = "Días por corrida: ";
    private final String ETQ_MEDIA = "Demanda promedio: ";
    private final String ETQ_DEV_EST = "Desviación Estándar: ";
    private final String ETQ_BTN_INICIAR = "iniciar Simulación";
    private JCheckBox jcbxPoliticas;
    private JTextField jtfPoliticas;
    private JTextField jtfCostoFaltante;
    private JTextField jtfCostoInventario;
    private JTextField jtfCorridas;
    private JTextField jtfDias;
    private JTextField jtfMedia;
    private JTextField jtfDevEstandar;
    private JButton jbnIniciar;
    private GridBagConstraints gbc;
    
    public VistaPrincipal() {
        addComponentes();
    }
    
    public final void addComponentes(){
        setLayout(new BorderLayout());
        
        gbc = new GridBagConstraints();
        jcbxPoliticas = new JCheckBox(ETQ_JCBX_SELECCION);
        jcbxPoliticas.setToolTipText("Seleccione esta casilla si desea introducir datos diferentes a los del enunciado.");
        jtfPoliticas = new JTextField(30);
        jtfPoliticas.setEnabled(false);
        jtfPoliticas.setToolTipText("Introduzca las políticas que quiera que se evaluen separadas por un espacio en blanco.");
        jtfCostoFaltante = new JTextField(7);
        jtfCostoFaltante.setEnabled(false);
        jtfCostoInventario = new JTextField(7);
        jtfCostoInventario.setEnabled(false);
        jtfCorridas = new JTextField(7);
        jtfCorridas.setEnabled(false);
        jtfDias = new JTextField(7);
        jtfDias.setEnabled(false);
        jtfMedia = new JTextField(7);
        jtfMedia.setEnabled(false);
        jtfDevEstandar = new JTextField(7);
        jtfDevEstandar.setEnabled(false);
        jbnIniciar = new JButton(ETQ_BTN_INICIAR);
        
        JTextArea enunciado =  new JTextArea(texto);
        enunciado.setEditable(false);
        enunciado.setFont(new Font("Arial", Font.PLAIN, 11));
        enunciado.setBorder(BorderFactory.createEtchedBorder());
        JPanel pInterno = new JPanel(new BorderLayout(10, 10));
        pInterno.setBorder(BorderFactory.createEtchedBorder());
        JLabel encabezado = new JLabel("Enunciado del problema");
        encabezado.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        pInterno.add(encabezado,"North");
        JScrollPane scroll = new JScrollPane(enunciado);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pInterno.add(scroll, "Center");
        add(pInterno, BorderLayout.NORTH);
        
        pInterno = new JPanel(new GridBagLayout());
        setGBC(0, 0, GridBagConstraints.FIRST_LINE_END, new Insets(5, 5, 5, 5));
        pInterno.add(jcbxPoliticas, gbc);
        setGBC(0, 1);
        pInterno.add(new JLabel(ETQ_POLITICAS), gbc);
        setGBC(1, 1);
        gbc.gridwidth = 3;
        pInterno.add(jtfPoliticas, gbc);
        
        setGBC(0, 2);
        gbc.gridwidth = 1;
        pInterno.add(new JLabel(ETQ_COSTO_FALTANTE), gbc);
        setGBC(1, 2, GridBagConstraints.FIRST_LINE_END,gbc.insets);
        pInterno.add(jtfCostoFaltante, gbc);
        
        setGBC(2, 2);
        pInterno.add(new JLabel(ETQ_COSTO_INVENTARIO), gbc);
        setGBC(3, 2);
        pInterno.add(jtfCostoInventario, gbc);
        
        setGBC(0, 4,GridBagConstraints.FIRST_LINE_END,gbc.insets);
        pInterno.add(new JLabel(ETQ_CORRIDAS), gbc);
        setGBC(1, 4);
        pInterno.add(jtfCorridas, gbc);
        
        setGBC(2, 4);
        pInterno.add(new JLabel(ETQ_DIAS), gbc);
        setGBC(3, 4);
        pInterno.add(jtfDias, gbc);
        
        setGBC(0, 5, GridBagConstraints.FIRST_LINE_END,gbc.insets);
        pInterno.add(new JLabel(ETQ_MEDIA), gbc);
        setGBC(1, 5);
        pInterno.add(jtfMedia, gbc);
        
        setGBC(2, 5);
        pInterno.add(new JLabel(ETQ_DEV_EST), gbc);
        setGBC(3, 5);
        pInterno.add(jtfDevEstandar, gbc);
        
        setGBC(0, 6, GridBagConstraints.CENTER, gbc.insets);
        gbc.gridwidth = 4;
        pInterno.add(jbnIniciar, gbc);
        add(pInterno, BorderLayout.CENTER);
    }
    
    private void setGBC(int gridx, int gridy, int anchor, Insets insets){
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = insets;
    }
    
    private void setGBC(int gridx, int gridy){
        setGBC(gridx, gridy, gbc.anchor, gbc.insets);
    }
    
//    public void addEventos(Controlador c){
//        iniciar.addActionListener(c);
//        entrada.setInputVerifier(c);
//    }
}
