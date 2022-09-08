package memory;

import javax.swing.*;

public class Frame implements UIElement{
    public Frame(String content){
        new JFrame(content);
    }
    @Override
    public String getType() {
        return "frame";
    }
}
