/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import modelos.Corrida;

/**
 *
 * @author VREBO
 */
public class VistaCorrida extends JPanel {

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
        add(new JLabel("Costo Promedio de la corrida: $" + corrida.getCostoPromedioCorrida(), JLabel.RIGHT), BorderLayout.SOUTH);
    }

    class Tabla extends JTable {

        protected String[] columnToolTips = {
            "Días que duró la corrida.",
            "Producción diaria.",
            "Demanda aleatoria por día.",
            "Faltantes por día.",
            "Costo por elementos faltantes.",
            "Sobrantes por día.",
            "Costo por elementos sobrantes.",
            "Costo Total."
        };

        public Tabla(Object[][] rowData, Object[] columnNames) {
            super(rowData, columnNames);
            setColumnsSize();
        }

        @Override
        protected JTableHeader createDefaultTableHeader() {
            return new JTableHeader(columnModel) {
                @Override
                public String getToolTipText(MouseEvent e) {
                    java.awt.Point p = e.getPoint();
                    int index = columnModel.getColumnIndexAtX(p.x);
                    int realIndex = columnModel.getColumn(index).getModelIndex();
                    return columnToolTips[realIndex];
                }
            };
        }

        private void setColumnsSize() {
            for (int i = 0; i < 8; i++) {
                TableColumn columna = getColumnModel().getColumn(i);
                switch (i) {
                    case 0:
                        columna.setPreferredWidth(5);
                        break;
                    case 2:
                        columna.setPreferredWidth(50);
                    case 4:
                        columna.setPreferredWidth(50);
                        break;
                    case 6:
                        columna.setPreferredWidth(70);
                        break;
                    default:
                        columna.setPreferredWidth(30);
                        break;
                }

            }

        }
    }
}
