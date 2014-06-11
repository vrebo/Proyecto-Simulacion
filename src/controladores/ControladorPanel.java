package controladores;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import vistas.Panel;

/**
 *
 * @author VREBO
 */
public class ControladorPanel implements ItemListener {

    private final Panel panel;

    public ControladorPanel(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String origen = (String) e.getItem();
        Container p = (Container) panel.getComponent(1);
        CardLayout card = (CardLayout) p.getLayout();
        card.show(p, origen);
    }

}
