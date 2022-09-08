package memory;

import javax.swing.*;
import java.awt.*;

public class Label implements UIElement{
    public Label(String content) {
        new JLabel(content);
    }

    @Override
    public String getType() {
        return "label";
    }
}
