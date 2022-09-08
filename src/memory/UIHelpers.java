package memory;

import java.awt.*;

public class UIHelpers {
    public GridBagConstraints generateConstraints(int i, int j) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .5;
        c.weighty = .5;
        c.gridx = i;
        c.gridy = j;
        return c;
    }
}
