package uiComponents;

import javax.swing.*;
import java.awt.*;

public class Frame implements UIElement{

    @Override
    public Component getElement(String content) {
        return new JFrame(content);
    }

    @Override
    public String getType() {
        return "frame";
    }
}
