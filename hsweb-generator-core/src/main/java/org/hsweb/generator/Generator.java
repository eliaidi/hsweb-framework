package org.hsweb.generator;

public final class Generator {
    public static final void execute(GeneratorConfiguration configuration) throws Exception {
        int step = 1;
        configuration.getCallBack().process(step++, "start");
        for (CodeTemplate template : configuration.getTemplates()) {
            configuration.getCallBack().process(step, "out put template:" + template);
            template.getOutput().output();
        }
        configuration.getCallBack().process(step++, "done");
    }
}
