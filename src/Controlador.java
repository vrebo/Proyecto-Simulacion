
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import vistas.VistaPrincipal;

/**
 *
 * @author VREBO
 */
public class Controlador extends InputVerifier implements ActionListener {

    private VistaPrincipal vista;

    public Controlador(VistaPrincipal vista) {
        this.vista = vista;
    }

    @Override
    public boolean verify(JComponent input) {
        JTextField entrada = (JTextField) input;
        String regex = "[0-9]+";
        String texto = entrada.getText().replaceAll("[ ]+", "");
        if (texto.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
}