package modelos;

/**
 *
 * @author VREBO
 */
public class Corrida {

    private final int produccionDiaria;
    private final double costoFaltante;
    private final double costoInventario;
    private final int dias;
    private final double media;
    private final double desvEstandar;
    private double costoPromedioCorrida;
    private final String[][] datos;

    public Corrida(int produccionDiaria, double costoFaltante, double costoInventario, int dias, double media, double desvEstandar) {
        this.produccionDiaria = produccionDiaria;
        this.costoFaltante = costoFaltante;
        this.costoInventario = costoInventario;
        this.dias = dias;
        this.media = media;
        this.desvEstandar = desvEstandar;
        datos = new String[7][dias];
    }

    private void iniciaSimulacion() {
        int demanda;
        int faltantes;
        int sobrantes = 0;
        
        for (int i = 0; i < dias; i++) {
            faltantes = 0;
            datos[i][0] = i + 1 + "";
            datos[i][1] = produccionDiaria + sobrantes + "";
            double r = 0;
            for (int j = 0; j < 12; j++) {
                r += Math.random();
            }
            demanda = (int)((r - 6) * desvEstandar + media);
            datos[i][2] = demanda + "";
            if(demanda - produccionDiaria >= 0){
                faltantes = demanda - produccionDiaria;
                datos[i][7] = faltantes * costoFaltante + "";
                costoPromedioCorrida += faltantes * costoFaltante;
                sobrantes = 0;
            }else{
                sobrantes = produccionDiaria - demanda;
                datos[i][7] = sobrantes * costoInventario + "";
                costoPromedioCorrida += sobrantes * costoInventario;
                faltantes = 0;
            }
            datos[i][3] = faltantes + "";
            datos[i][4] = faltantes * costoFaltante + "";
            datos[i][5] = sobrantes + "";
            datos[i][6] = sobrantes * costoInventario + "";
        }
        costoPromedioCorrida /= dias;
    }

    public double getCostoPromedioCorrida() {
        return costoPromedioCorrida;
    }

    public String[][] getDatos() {
        return datos;
    }
}