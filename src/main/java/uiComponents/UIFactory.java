package uiComponents;

public class UIFactory  {
    public UIElement getUIElement(UIType type){
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
    public UIElement getPanelFlowElement(){
        return new PanelFlow();
    }
    public UIElement getPanelGridElement(){
        return new PanelGrid();
    }
}


