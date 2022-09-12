package uiComponents;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Component;

public class PanelGrid implements UIElement{
    @Override
    public Component getElement(String content) {
        return new JPanel(new GridBagLayout());
    }
    @Override
    public String getType() {
        return "panel grid";
    }

}
