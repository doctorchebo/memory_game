package memory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static memory.UITypes.*;

public class UIFactory {
    public Container UIFactory(UITypes UIType, String content){
        switch (UIType){
            case BUTTON:
                JButton button = new JButton(content);
                enlargeFont(button, 2);
                return button;
            case TITLE:
                return new JLabel(content);
            case FRAME:
                JFrame frame = new JFrame(content);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                return frame;
            case PANEL:
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER
                        , 50, 0));
                panel.setBorder(new BevelBorder(BevelBorder.RAISED));
                return panel;
            default: return null;
        }
    }

    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }
}
