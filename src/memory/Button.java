package memory;

import javax.swing.*;

public class Button implements UIElement{
    public Button (String content){
        new JButton(content);
    }

    @Override
    public String getType() {
        return "button";
    }

}
