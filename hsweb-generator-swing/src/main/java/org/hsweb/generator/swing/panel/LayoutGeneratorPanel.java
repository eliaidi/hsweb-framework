package org.hsweb.generator.swing.panel;

import org.hsweb.generator.swing.SwingGeneratorApplication;

import java.awt.*;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public abstract class LayoutGeneratorPanel extends GeneratorPanel {

    protected SwingGeneratorApplication application;

    public abstract Component[][] getComponentArray();

    public void layoutComponents() {
        int x = 20, y = 20;
        for (Component[] component : getComponentArray()) {
            int maxH = 0;
            //横向:
            for (Component componentX : component) {
                if (maxH < componentX.getHeight())
                    maxH = componentX.getHeight();
                componentX.setLocation(x, y);
                x += componentX.getWidth() + 10;
                this.add(componentX);
            }
            x = 20;
            y += maxH + 5;
        }
    }

    @Override
    public void init(SwingGeneratorApplication application) {
        this.application = application;
        setLayout(null);
    }
}
