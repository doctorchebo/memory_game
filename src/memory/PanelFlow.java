package memory;

import javax.swing.*;
import java.awt.*;

public class PanelFlow implements UIElement{
    public PanelFlow(FlowType flowType){
        new JPanel(new FlowLayout(FlowLayout.LEADING));
    }
    @Override
    public String getType() {
        return "panel flow";
    }

}
