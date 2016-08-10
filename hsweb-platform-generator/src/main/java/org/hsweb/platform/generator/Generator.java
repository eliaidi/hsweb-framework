package org.hsweb.platform.generator;

import org.hsweb.platform.generator.template.CodeTemplate;

import java.util.List;

public interface Generator<IN> {

    void start(CodeTemplate<IN> codeTemplate, CodeTemplate<IN>... codeTemplates);
}
