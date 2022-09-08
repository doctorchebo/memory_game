package memory;

import javax.swing.*;
import java.awt.*;

import static memory.FlowType.CENTER;
import static memory.FlowType.LEADING;

public class UIFactory  {
    UIElement getUIElement(UIType type, String content){
        switch (type){
            case BUTTON:
                return new Button(content);
            case FRAME:
                return new Frame(content);
            case LABEL:
                return new Label(content);
            default: return null;
        }
    }
    UIElement getPanelFlowElement(FlowType flowType){
        return new PanelFlow(flowType);
    }
    UIElement getPanelGridElement(){
        return new PanelGrid();
    }
}


