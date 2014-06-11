package controladores;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelos.Corrida;
import vistas.VistaResultados;
import vistas.VistaPrincipal;
import vistas.Panel;
import vistas.VistaCorrida;

/**
 *
 * @author VREBO
 */
public class ControladorVistaPrincipal extends InputVerifier implements ActionListener, ItemListener {

    private final VistaPrincipal vPrincipal;

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal) {
        this.vPrincipal = vistaPrincipal;
    }

    @Override
    public boolean verify(JComponent input) {
        if (input.equals(vPrincipal.getJtfPoliticas())) {
            String regex = "([0-9]+|[ ]+)+";
            return ((JTextField) input).getText().trim().matches(regex);
        }
        if (input.equals(vPrincipal.getJtfCostoFaltante()) || input.equals(vPrincipal.getJtfCostoInventario())) {
            String regex = "(([0-9]+.[0-9]+)+|([0-9]+)+)";
            return ((JTextField) input).getText().trim().matches(regex);
        }
        System.out.println("ñe");
        String regex = "([0-9]+)+";
        return ((JTextField) input).getText().matches(regex);
//        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            vPrincipal.getJtfPoliticas().setText("");
            vPrincipal.getJtfPoliticas().setEditable(true);
            vPrincipal.getJtfCostoFaltante().setText("");
            vPrincipal.getJtfCostoFaltante().setEditable(true);
            vPrincipal.getJtfCostoInventario().setText("");
            vPrincipal.getJtfCostoInventario().setEditable(true);
            vPrincipal.getJtfCorridas().setText("");
            vPrincipal.getJtfCorridas().setEditable(true);
            vPrincipal.getJtfDias().setText("");
            vPrincipal.getJtfDias().setEditable(true);
            vPrincipal.getJtfMedia().setText("");
            vPrincipal.getJtfMedia().setEditable(true);
            vPrincipal.getJtfDevEstandar().setText("");
            vPrincipal.getJtfDevEstandar().setEditable(true);
        } else {
            vPrincipal.getJtfPoliticas().setText(VistaCorrida.POLITICAS);
            vPrincipal.getJtfPoliticas().setEditable(false);
            vPrincipal.getJtfCostoFaltante().setText(Corrida.COSTO_X_FALTANTE);
            vPrincipal.getJtfCostoFaltante().setEditable(false);
            vPrincipal.getJtfCostoInventario().setText(Corrida.COSTO_X_INVENTARIO);
            vPrincipal.getJtfCostoInventario().setEditable(false);
            vPrincipal.getJtfCorridas().setText(VistaCorrida.CORRIDAS);
            vPrincipal.getJtfCorridas().setEditable(false);
            vPrincipal.getJtfDias().setText(Corrida.DIAS);
            vPrincipal.getJtfDias().setEditable(false);
            vPrincipal.getJtfMedia().setText(Corrida.MEDIA);
            vPrincipal.getJtfMedia().setEditable(false);
            vPrincipal.getJtfDevEstandar().setText(Corrida.DEV_ESTANDAR);
            vPrincipal.getJtfDevEstandar().setEditable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vPrincipal.getJcbxPoliticas().isSelected()) {
            if (verify(vPrincipal.getJtfPoliticas())) {
                if (verify(vPrincipal.getJtfCostoFaltante())) {
                    if (verify(vPrincipal.getJtfCostoInventario())) {
                        if (verify(vPrincipal.getJtfCorridas())) {
                            if (verify(vPrincipal.getJtfDias())) {
                                if (verify(vPrincipal.getJtfMedia())) {
                                    if (verify(vPrincipal.getJtfDevEstandar())) {
                                        simulacion(new VistaResultados());
                                    } else {
                                        JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en \"Desviación estándar\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en \"Demanda promedio\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en \"Días por corrida\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en \"Número de corridas\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en el \"Costo por inventario\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en \"Costo por faltante\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vPrincipal, "Valor invalido en \"Políticas de producción\".", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            simulacionDefault(new VistaResultados());
        }
    }

    private void simulacionDefault(VistaResultados vResultados) {
        String[] politicasString = VistaCorrida.POLITICAS.split(" ");
        int[] politicas = new int[politicasString.length];
        int numCorridas;

        for (int i = 0; i < politicas.length; i++) {
            politicas[i] = Integer.parseInt(politicasString[i]);
        }

        numCorridas = Integer.parseInt(VistaCorrida.CORRIDAS);
        int indiceMejorPolitica = -1;
        double promedioCorridas, mejorPolitica = Double.MAX_VALUE;
        for (int i = 0; i < politicas.length; i++) {
            promedioCorridas = 0;
            vResultados.addPestana("Política " + politicas[i]);
            Panel panel = (Panel) vResultados.getPestana(i);
            for (int j = 0; j < numCorridas; j++) {
                panel.getCorridas().addItem(j + 1 + "");
                Corrida corrida = new Corrida(politicas[i]);
                VistaCorrida vCorrida = new VistaCorrida(corrida);
                promedioCorridas += corrida.getCostoPromedioCorrida();
                panel.addCorrida(vCorrida, j + 1 + "");
            }
            promedioCorridas /= numCorridas;
            if (mejorPolitica > promedioCorridas) {
                mejorPolitica = promedioCorridas;
                indiceMejorPolitica = i;
            }
            panel.setjLblPromedioCorridas("Costo Promedio de todas las corridas $" + promedioCorridas);
        }
        indicaMejorPolitica(vResultados,indiceMejorPolitica,mejorPolitica);
    }

    private void simulacion(VistaResultados vResultados) {
        String texto = vPrincipal.getJtfPoliticas().getText().trim();
        String[] politicasString = texto.split("[ ]+");
        int[] politicas = new int[politicasString.length];
        int numCorridas, dias, media, devEstandar;
        double costoFaltante, costoInventario;
        for (int i = 0; i < politicas.length; i++) {
            politicas[i] = Integer.parseInt(politicasString[i]);
        }
        Arrays.sort(politicas);
        costoFaltante = Double.parseDouble(vPrincipal.getJtfCostoFaltante().getText());
        costoInventario = Double.parseDouble(vPrincipal.getJtfCostoInventario().getText());
        numCorridas = Integer.parseInt(vPrincipal.getJtfCorridas().getText());
        dias = Integer.parseInt(vPrincipal.getJtfDias().getText());
        media = Integer.parseInt(vPrincipal.getJtfMedia().getText());
        devEstandar = Integer.parseInt(vPrincipal.getJtfDevEstandar().getText());

        int indiceMejorPolitica = -1;
        double promedioCorridas, mejorPolitica = Double.MAX_VALUE;
        for (int i = 0; i < politicas.length; i++) {
            promedioCorridas = 0;
            vResultados.addPestana("Política " + politicas[i]);
            Panel panel = (Panel) vResultados.getPestana(i);
            for (int j = 0; j < numCorridas; j++) {
                panel.getCorridas().addItem(j + 1 + "");
                Corrida corrida = new Corrida(politicas[i], costoFaltante,
                        costoInventario, dias, media, devEstandar);
                VistaCorrida vCorrida = new VistaCorrida(corrida);
                promedioCorridas += corrida.getCostoPromedioCorrida();
                panel.addCorrida(vCorrida, j + 1 + "");
            }
            promedioCorridas /= numCorridas;
            if (mejorPolitica > promedioCorridas) {
                mejorPolitica = promedioCorridas;
                indiceMejorPolitica = i;
            }
            panel.setjLblPromedioCorridas("Costo Promedio de todas las corridas $" + promedioCorridas);
        }
        indicaMejorPolitica(vResultados,indiceMejorPolitica,mejorPolitica);
    }

    private void indicaMejorPolitica(VistaResultados vResultados, int index, double costoTotal) {
        vResultados.getPestanas().getComponentAt(index).setBackground(Color.GREEN);
        String url = "/iconos/solucionx16.png";
        ImageIcon icono = new ImageIcon(getClass().getResource(url));
        vResultados.getPestanas().setIconAt(index, icono);
        String politica = vResultados.getPestanas().getTitleAt(index).split(" ")[1];
        url = "/iconos/solucionx32.png";
        icono = new ImageIcon(getClass().getResource(url));
        JOptionPane.showMessageDialog(vResultados,
                "Política de producción mas conveniente: " + politica
                + " Refrigeradores por día"
                + "\nCosto total: $" + costoTotal
                + " pesos por día", "Simulación Terminada", JOptionPane.INFORMATION_MESSAGE, icono);
    }
}
