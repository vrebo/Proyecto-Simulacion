package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
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
        String regex;
        if (input.equals(vPrincipal.getJtfPoliticas())) {
            regex = "([0-9]+|[ ]+)+";
            return ((JTextField) input).getText().trim().matches(regex);
        }
        if (input.equals(vPrincipal.getJtfCostoFaltante()) || input.equals(vPrincipal.getJtfCostoInventario())) {
            regex = "(([0-9]+.[0-9]+)+|([0-9]+)+)";
            return ((JTextField) input).getText().trim().matches(regex);
        }
        regex = "([0-9]+)+";
        return ((JTextField) input).getText().matches(regex);
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
            vPrincipal.getJtfCostoFaltante().setText(Corrida.COSTO_X_FALTANTE + "");
            vPrincipal.getJtfCostoFaltante().setEditable(false);
            vPrincipal.getJtfCostoInventario().setText(Corrida.COSTO_X_INVENTARIO + "");
            vPrincipal.getJtfCostoInventario().setEditable(false);
            vPrincipal.getJtfCorridas().setText(VistaCorrida.CORRIDAS + "");
            vPrincipal.getJtfCorridas().setEditable(false);
            vPrincipal.getJtfDias().setText(Corrida.DIAS + "");
            vPrincipal.getJtfDias().setEditable(false);
            vPrincipal.getJtfMedia().setText(Corrida.MEDIA + "");
            vPrincipal.getJtfMedia().setEditable(false);
            vPrincipal.getJtfDevEstandar().setText(Corrida.DEV_ESTANDAR + "");
            vPrincipal.getJtfDevEstandar().setEditable(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vPrincipal.getJcbxPoliticas().isSelected()) {
            String advertencia = "";
            if (!verify(vPrincipal.getJtfPoliticas())) {
                advertencia += "Valor invalido en \"Políticas de producción\".\n";
            }
            if (!verify(vPrincipal.getJtfCostoFaltante())) {
                advertencia += "Valor invalido en \"Costo por faltante\".\n";
            }
            if (!verify(vPrincipal.getJtfCostoInventario())) {
                advertencia += "Valor invalido en el \"Costo por inventario\".\n";
            }
            if (!verify(vPrincipal.getJtfCorridas())) {
                advertencia += "Valor invalido en \"Número de corridas\".\n";
            }
            if (!verify(vPrincipal.getJtfDias())) {
                advertencia += "Valor invalido en \"Días por corrida\".\n";
            }
            if (!verify(vPrincipal.getJtfMedia())) {
                advertencia += "Valor invalido en \"Demanda promedio\".\n";
            }
            if (!verify(vPrincipal.getJtfDevEstandar())) {
                advertencia += "Valor invalido en \"Desviación estándar\".\n";
            }
            if (advertencia.equals("")) {
                simulacion();
            } else {
                JOptionPane.showMessageDialog(vPrincipal, advertencia, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            simulacionDefault();
        }
    }

    private void simulacionDefault() {
        String[] politicasString = VistaCorrida.POLITICAS.split(" ");
        int[] politicas = new int[politicasString.length];
        for (int i = 0; i < politicas.length; i++) {
            politicas[i] = Integer.parseInt(politicasString[i]);
        }
        addVistasCorrida(politicas, VistaCorrida.CORRIDAS);
    }

    private void simulacion() {
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

        addVistasCorrida(politicas, numCorridas, costoFaltante, costoInventario, dias, media, devEstandar);
    }

    private void addVistasCorrida(int[] politicas, int numCorridas) {
        addVistasCorrida(politicas, numCorridas,
                Corrida.COSTO_X_FALTANTE, Corrida.COSTO_X_INVENTARIO,
                Corrida.DIAS, Corrida.MEDIA, Corrida.DEV_ESTANDAR);
    }

    private void addVistasCorrida(int[] politicas,
            int numCorridas, double costoFaltante, double costoInventario,
            int dias, int media, int devEstandar) {
        VistaResultados vResultados = new VistaResultados();
        int indiceMejorPolitica = -1;
        double costoPromedio, desvEstandar = 0, mejorPolitica = Double.MAX_VALUE;
        VistaCorrida[] vCorridas = new VistaCorrida[numCorridas];
        for (int i = 0; i < politicas.length; i++) {
            costoPromedio = 0;
            desvEstandar = 0;
            vResultados.addPestana("Política " + politicas[i]);
            Panel panel = (Panel) vResultados.getPestana(i);
            for (int j = 0; j < numCorridas; j++) {
                panel.getCorridas().addItem(j + 1 + "");
                Corrida corrida = new Corrida(politicas[i], costoFaltante,
                        costoInventario, dias, media, devEstandar);
                vCorridas[j] = new VistaCorrida(corrida);
                costoPromedio += corrida.getCostoPromedioCorrida();
            }
            costoPromedio /= numCorridas;
            if (mejorPolitica > costoPromedio) {
                mejorPolitica = costoPromedio;
                indiceMejorPolitica = i;
            }
            String datosCorrida = "Costo Promedio de todas las corridas: $" + costoPromedio;
            for (int j = 0; j < numCorridas; j++) {
                double x = vCorridas[j].getCorrida().getCostoPromedioCorrida();
                desvEstandar += Math.pow(x - costoPromedio, 2);
                panel.addCorrida(vCorridas[j], j + 1 + "");
            }
            desvEstandar /= (dias - 1);
            desvEstandar = Math.pow(desvEstandar, .5);
            datosCorrida += " Desviación estandar: " + desvEstandar + "";
            panel.setjLblPromedioCorridas(datosCorrida);
        }

        indicaMejorPolitica(vResultados, indiceMejorPolitica, mejorPolitica, desvEstandar);
    }

    private void indicaMejorPolitica(VistaResultados vResultados, int index, double costoTotal, double desvEstandar) {
        JTabbedPane pestanas = vResultados.getPestanas();
        String url;
        ImageIcon icono;
        String politica = vResultados.getPestanas().getTitleAt(index).split(" ")[1];
        for (int i = pestanas.getTabCount() - 1; i >= 0; i--) {
            if (i == index) {
                url = "/iconos/solucionx16.png";
                pestanas.getComponentAt(i).setBackground(new Color(154, 254, 46));
                icono = new ImageIcon(getClass().getResource(url));
                pestanas.setIconAt(i, icono);
            } else {
                url = "/iconos/no-solucionx16.png";
                pestanas.getComponentAt(i).setBackground(new Color(88, 211, 247));
                icono = new ImageIcon(getClass().getResource(url));
                pestanas.setIconAt(i, icono);
            }
        }
        url = "/iconos/solucionx32.png";
        icono = new ImageIcon(getClass().getResource(url));
        JOptionPane.showMessageDialog(vResultados,
                "Política de producción mas conveniente: " + politica
                + " Refrigeradores por día"
                + "\nCosto total promedio: $" + costoTotal
                + " pesos por día\n"
                + "Desviación estándar: " + desvEstandar, "Simulación Terminada", JOptionPane.INFORMATION_MESSAGE, icono);
    }
}
