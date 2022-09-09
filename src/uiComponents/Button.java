package uiComponents;

import javax.swing.*;
import java.awt.*;

public class Button implements UIElement {

    @Override
    public String getType() {
        return "button";
    }

    @Override
    public Component getElement(String content) {
        return new JButton(content);
    }

}
