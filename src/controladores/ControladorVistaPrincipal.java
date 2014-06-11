package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import modelos.Corrida;
import vistas.VistaResultados;
import vistas.VistaPrincipal;
import vistas.Panel;
import vistas.VistaCorrida;

/**
 *
 * @author VREBO
 */
public class ControladorVistaPrincipal extends InputVerifier implements ActionListener {

    private final VistaPrincipal vPrincipal;

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal) {
        this.vPrincipal = vistaPrincipal;
    }

    @Override
    public boolean verify(JComponent input) {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VistaResultados vResultados = new VistaResultados();
        System.out.println("evento");
        if (vPrincipal.getJcbxPoliticas().isSelected()) {

        } else {
            simulacionDefault(vResultados);
        }
    }

    private void simulacionDefault(VistaResultados vResultados) {
        int numCorridas, dias, media, desvEstandar;
        double costoFaltante, costoInventario;
        int[] politicas = {60, 70, 80, 90, 100};
        String politica = "";
        for (int i = 0; i < politicas.length; i++) {
            if (i == politicas.length - 1) {
                politica += politicas[i];
            } else {
                politica += politicas[i] + ", ";
            }
        }
        vPrincipal.getJtfPoliticas().setText(politica);
        costoFaltante = 8;
        vPrincipal.getJtfCostoFaltante().setText(costoFaltante + "");
        costoInventario = 5;
        vPrincipal.getJtfCostoInventario().setText(costoInventario + "");
        numCorridas = 5;
        vPrincipal.getJtfCorridas().setText(numCorridas + "");
        dias = 100;
        vPrincipal.getJtfDias().setText(dias + "");
        media = 80;
        vPrincipal.getJtfMedia().setText(media + "");
        desvEstandar = 10;
        vPrincipal.getJtfDevEstandar().setText(desvEstandar + "");

        for (int i = 0; i < politicas.length; i++) {
            vResultados.addPestana("Politica " + politicas[i]);
            Panel panel = (Panel) vResultados.getPestana(i);
            for (int j = 0; j < numCorridas; j++) {
                panel.getCorridas().addItem(j + 1 + "");
                VistaCorrida vCorrida = new VistaCorrida(new Corrida(politicas[i], costoFaltante, costoInventario, dias, media, desvEstandar));
                panel.addCorrida(vCorrida, j + 1 + "");
                System.out.println("agregando corrida");
            }
        }
    }
}
