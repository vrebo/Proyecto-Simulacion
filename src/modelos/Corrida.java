package modelos;

/**
 *
 * @author VREBO
 */
public class Corrida {

    public static String COSTO_X_FALTANTE = "8";
    public static String COSTO_X_INVENTARIO = "5";
    public static String DIAS = "100";
    public static String DEV_ESTANDAR = "10";
    public static String MEDIA = "80";
    
    private final int produccionDiaria;
    private final double costoFaltante;
    private final double costoInventario;
    private final int dias;
    private final int media;
    private final int desvEstandar;
    private double costoPromedioCorrida;
    private final String[][] datos;

    public Corrida(int produccionDiaria) {
        this(produccionDiaria, 8d, 5d, 100, 80, 10);
    }

    public Corrida(int produccionDiaria, double costoFaltante, double costoInventario, int dias, int media, int desvEstandar) {
        this.produccionDiaria = produccionDiaria;
        this.costoFaltante = costoFaltante;
        this.costoInventario = costoInventario;
        this.dias = dias;
        this.media = media;
        this.desvEstandar = desvEstandar;
        datos = new String[dias][8];
        iniciaSimulacion();
    }

    private void iniciaSimulacion() {
        int demanda;
        int faltantes;
        int sobrantes = 0;

        for (int i = 0; i < dias; i++) {
            datos[i][0] = i + 1 + "";
            datos[i][1] = produccionDiaria + sobrantes + "";
            double r = 0;
            for (int j = 0; j < 12; j++) {
                r += Math.random();
            }
            demanda = (int) ((r - 6) * desvEstandar + media);
            datos[i][2] = demanda + "";
            if (demanda - produccionDiaria >= 0) {
                faltantes = demanda - produccionDiaria;
                datos[i][7] = faltantes * costoFaltante + "";
                costoPromedioCorrida += faltantes * costoFaltante;
                sobrantes = 0;
            } else {
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
