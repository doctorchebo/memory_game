package memory;


import java.awt.Component;

public class FontEnlarger {
    public void enlargeFont(Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }
}
