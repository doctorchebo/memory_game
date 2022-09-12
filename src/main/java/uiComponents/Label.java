package uiComponents;

import javax.swing.*;
import java.awt.*;

public class Label implements UIElement{
    @Override
    public String getType() {
        return "label";
    }

    @Override
    public Component getElement(String content) {
        return new JLabel(content);
    }

}
