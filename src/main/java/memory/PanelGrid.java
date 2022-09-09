package memory;

import javax.swing.*;
import java.awt.*;

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
