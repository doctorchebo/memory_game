package memory;

import javax.swing.*;
import java.awt.*;

import static memory.FlowType.*;

public class PanelFlow implements UIElement{

    @Override
    public String getType() {
        return "panel flow";
    }

    @Override
    public Component getElement(String content) {
        switch (content) {
            case "LEADING":
                return new JPanel(new FlowLayout(FlowLayout.LEADING));
            case "CENTER":
                return new JPanel(new FlowLayout(FlowLayout.CENTER, 50,0));
            default:
                return null;
        }
    }
}
