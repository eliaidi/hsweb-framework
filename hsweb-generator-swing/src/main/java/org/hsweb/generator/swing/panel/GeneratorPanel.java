package org.hsweb.generator.swing.panel;

import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.config.Configurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.Serializable;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public abstract class GeneratorPanel extends JPanel implements Configurable<Serializable> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract String getTooltip();

    public abstract String getTitle();

    public abstract void init(SwingGeneratorApplication application);

    public abstract void onSelected();

    @Override
    public void load(Serializable o) {

    }

    @Override
    public Serializable getConfig() {
        return null;
    }

    @Override
    public String getConfigName() {
        return this.getClass().getSimpleName().concat(".cfg");
    }
}
