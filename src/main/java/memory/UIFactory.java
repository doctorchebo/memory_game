package memory;

public class UIFactory  {
    UIElement getUIElement(UIType type){
        switch (type){
            case BUTTON:
                return new Button();
            case FRAME:
                return new Frame();
            case LABEL:
                return new Label();
            default: return null;
        }
    }
    UIElement getPanelFlowElement(){
        return new PanelFlow();
    }
    UIElement getPanelGridElement(){
        return new PanelGrid();
    }
}


