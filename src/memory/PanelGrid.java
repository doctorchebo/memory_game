package memory;

import javax.swing.*;
import java.awt.*;

public class PanelGrid implements UIElement{
    public PanelGrid(){
        new JPanel(new GridBagLayout());
    }
    @Override
    public String getType() {
        return "panel grid";
    }

}
