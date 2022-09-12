package uiComponents;

import java.awt.Component;

public interface UIElement {
    String getType();
    Component getElement(String content);
}
